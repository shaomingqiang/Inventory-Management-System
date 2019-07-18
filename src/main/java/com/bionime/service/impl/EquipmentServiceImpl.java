package com.bionime.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.EquipmentMapper;
import com.bionime.mapper.EquipmentTypeMapper;
import com.bionime.pojo.Equipment;
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
	private EquipmentTypeMapper EquipmentTypeMapper;

	@Override
	public SystemResult insert(Equipment equipment) {
		List<Equipment> equipmentsList = new ArrayList<Equipment>();
		List<String> snList = Arrays.asList(equipment.getSn().split(","));
		List<String> propertyNoList = null;
		int snSize = snList.size();
		boolean hasPropertyNo = false;
		if (equipment.getProperty_no() != null && !("".equals(equipment.getProperty_no()))) {
			propertyNoList = Arrays.asList(equipment.getProperty_no().split(","));
			if (snSize != propertyNoList.size()) {
				return SystemResult.build(500, "序列号数量与财产编号数量不一致！请重新录入！");
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
		EquipmentTypeMapper.countIncrease(equipment.getEt_id(), snSize);
		return SystemResult.ok();
	}

	@Override
	public SystemResult selectByType(EquipmentType equipmentType) {
		List<Equipment> equipmentList = equipmentMapper.selectByType(equipmentType);
		return SystemResult.ok(equipmentList);
	}

}
