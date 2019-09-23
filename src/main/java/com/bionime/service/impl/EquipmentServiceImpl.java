package com.bionime.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.EquipmentMapper;
import com.bionime.mapper.EquipmentTypeMapper;
import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;
import com.bionime.service.EquipmentService;
import com.bionime.utils.SystemResult;

/**
 * 
 * <p>
 * Title: EquipmentServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.bionime.com
 * </p>
 * 
 * @author Ethan.Shao
 * @date 2019年7月11日下午2:30:10
 * @version 1.0
 */
@Transactional
@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;

	@Override
	public SystemResult insert(Equipment equipment) {
		List<Equipment> equipmentsList = new ArrayList<Equipment>();
		List<String> snList = Arrays.asList(equipment.getSn().split(","));
		List<String> propertyNoList = null;
		int snSize = snList.size();
		boolean hasPropertyNo = false;
		if (equipment.getProperty_no() != null && !"".equals(equipment.getProperty_no())) {
			if (equipment.getProperty_no().contains("，")) {
				return SystemResult.build(500, "序列号之间请使用英文逗号，请重新录入！");
			}
			propertyNoList = Arrays.asList(equipment.getProperty_no().split(","));
			if (snSize != propertyNoList.size()) {
				return SystemResult.build(500, "序列号数量与财产编号数量不一致，请重新录入！");
			}
			hasPropertyNo = true;
		}
		List<Equipment> equipmentsExist = equipmentMapper.selectBySn(snList);
		List<String> duplicatedSns = new ArrayList<String>();
		if (equipmentsExist != null && equipmentsExist.size() != 0) {
			for (Equipment equipentTemp : equipmentsExist) {
				duplicatedSns.add(equipentTemp.getSn());
			}
			return SystemResult.build(500, "序列号" + duplicatedSns.toString() + "已存在，请重新录入！");
		}
		for (int i = 0; i < snSize; i++) {
			Equipment equipmentForInsert = new Equipment();
			equipmentForInsert.setSn(snList.get(i));
			equipmentForInsert.setIn_time(new Date());
			equipmentForInsert.setDescription(equipment.getDescription());
			equipmentForInsert.setEt_id(equipment.getEt_id());
			if (hasPropertyNo) {
				equipmentForInsert.setProperty_no(propertyNoList.get(i));
			}
			equipmentsList.add(equipmentForInsert);
		}
		equipmentMapper.insert(equipmentsList);
		equipmentTypeMapper.countIncrease(equipment.getEt_id(), snSize);
		return SystemResult.ok();
	}

	@Override
	public SystemResult selectByType(EquipmentType equipmentType) {
		List<Equipment> equipmentList = equipmentMapper.selectByType(equipmentType);
		return SystemResult.ok(equipmentList);
	}

	@Override
	public SystemResult statusChange(String id, int status, Long h_id, Long d_id) {
		List<String> ids = Arrays.asList(id.split(","));
		equipmentMapper.statusChange(ids, status, h_id, d_id);
		return SystemResult.ok();
	}

	@Override
	public SystemResult selectEquimentExt(EquipmentExt equipmentExt) {
		List<EquipmentExt> equipmentExtList = equipmentMapper.selectEquipmentExt(equipmentExt);
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("10", "在库");
		statusMap.put("20", "在院");
		statusMap.put("30", "出库");
		statusMap.put("40", "借用");
		statusMap.put("50", "返修");
		statusMap.put("60", "审核中");
		for (EquipmentExt equipmentExtItem : equipmentExtList) {
			equipmentExtItem.setIn_time(equipmentExtItem.getIn_time().substring(0, 10));
			equipmentExtItem.setStatus(statusMap.get(equipmentExtItem.getStatus()));
		}
		return SystemResult.ok(equipmentExtList.size() + "", equipmentExtList);
	}

	@Override
	public Map<String, Object> selectEquimentExtByPage(HashMap<String, Object> paramMap, EquipmentExt equipmentExt) {
		List<EquipmentExt> equimentExtListByPage = equipmentMapper.selectEquipmentExtByPage(paramMap);
		Map<String, String> STATUS_CODES = new HashMap<String, String>();
		STATUS_CODES.put("10", "在库");
		STATUS_CODES.put("20", "在院");
		STATUS_CODES.put("30", "出库");
		STATUS_CODES.put("40", "借用");
		STATUS_CODES.put("50", "返修");
		STATUS_CODES.put("60", "审核");
		for (EquipmentExt ext : equimentExtListByPage) {
			ext.setIn_time(ext.getIn_time().substring(0, 10));
			ext.setStatus(STATUS_CODES.get(ext.getStatus()));
		}
		List<EquipmentExt> equipmentExtList = equipmentMapper.selectEquipmentExt(equipmentExt);
		int count = equipmentExtList.size();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("data", equimentExtListByPage);
		map.put("count", count);
		return map;
	}

	@Override
	public SystemResult updateEquimentExt(EquipmentExt equipmentExt) {
		if (equipmentExt != null) {
			// 拿到设备id
			Long id = equipmentExt.getId();
			Long etId = null;
			String oldStatus = null;
			EquipmentType equipmentType = new EquipmentType();
			equipmentType.setName(equipmentExt.getName());
			equipmentType.setType(equipmentExt.getType());
			List<EquipmentType> equipmentTypeList = equipmentTypeMapper.selectEquipmentTypeByName(equipmentType);
			if (equipmentTypeList != null && equipmentTypeList.size() == 1) {
				etId = equipmentTypeList.get(0).getId();
			}
			equipmentExt.setEt_id(etId);
			// 根据设备id去查询设备的旧的序列号
			List<Equipment> selectEquiment = equipmentMapper.selectEquipmentById(id);
			String oldSn = "";
			if (selectEquiment != null && selectEquiment.size() == 1) {
				oldSn = selectEquiment.get(0).getSn();
				oldStatus = selectEquiment.get(0).getStatus();
			}
			if (!oldSn.equals(equipmentExt.getSn())) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
			}else if(!oldStatus.equals(equipmentExt.getStatus())) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
			} else {
				List<String> snList = Arrays.asList(equipmentExt.getSn().split(","));
				List<Equipment> equipmentsExist = equipmentMapper.selectBySn(snList);
				List<String> duplicatedSns = new ArrayList<String>();
				if (equipmentsExist != null && equipmentsExist.size() != 0) {
					for (Equipment equipentTemp : equipmentsExist) {
						duplicatedSns.add(equipentTemp.getSn());
					}
					return SystemResult.build(500, "序列号" + duplicatedSns.toString() + "已存在，请修改序列号！");
				}
			}
			return SystemResult.ok();
		} else {
			return SystemResult.build(400, "");
		}

	}

	@Override
	public SystemResult updateEquimentExtById(int id) {
		int updateEquimentExt = equipmentMapper.updateEquipmentExtById(id);
		return SystemResult.ok();
	}

	@Override
	public Map<String, Object> selectCountByStatus() {
		// 查询在库761meter
		Integer meter_761_10 = equipmentMapper.selectCountByStatus("761", 10, "血糖仪");
		// 查询在库760meter
		Integer meter_760_10 = equipmentMapper.selectCountByStatus("760", 10, "血糖仪");
		// 查询在库761holder
		Integer holder_761_10 = equipmentMapper.selectCountByStatus("761", 10, "holder");
		// 查询在库760holder
		Integer holder_760_10 = equipmentMapper.selectCountByStatus("761", 10, "holder");
		// 查询在院761meter
		Integer meter_761_20 = equipmentMapper.selectCountByStatus("761", 20, "血糖仪");
		// 查询在院760meter
		Integer meter_760_20 = equipmentMapper.selectCountByStatus("761", 20, "血糖仪");
		// 查询在库meter
		Integer meter_10 = equipmentMapper.selectCountByStatus("", 10, "血糖仪");
		// 查询在院meter
		Integer meter_20 = equipmentMapper.selectCountByStatus("", 20, "血糖仪");
		// 查询出库meter
		Integer meter_30 = equipmentMapper.selectCountByStatus("", 30, "血糖仪");
		// 查询借用meter
		Integer meter_40 = equipmentMapper.selectCountByStatus("", 40, "血糖仪");
		// 查询返修meter
		Integer meter_50 = equipmentMapper.selectCountByStatus("", 50, "血糖仪");
		// 查询审核meter
		Integer meter_60 = equipmentMapper.selectCountByStatus("", 60, "血糖仪");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("meter_761_10", meter_761_10);
		map.put("meter_760_10", meter_760_10);
		map.put("holder_761_10", holder_761_10);
		map.put("holder_760_10", holder_760_10);
		map.put("meter_761_20", meter_761_20);
		map.put("meter_760_20", meter_760_20);
		map.put("meter_10", meter_10);
		map.put("meter_20", meter_20);
		map.put("meter_30", meter_30);
		map.put("meter_40", meter_40);
		map.put("meter_50", meter_50);
		map.put("meter_60", meter_60);
		return map;
	}
}
