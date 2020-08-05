package com.bionime.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.EquipmentMapper;
import com.bionime.mapper.EquipmentRecordMapper;
import com.bionime.mapper.EquipmentTypeMapper;
import com.bionime.mapper.HospitalMapper;
import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentRecord;
import com.bionime.pojo.EquipmentType;
import com.bionime.pojo.Hospital;
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
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;
	
	@Autowired
	private EquipmentRecordMapper equipmentRecordMapper;
	
	@Autowired
	private HospitalMapper hospitalMapper;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;

	@Override
	public SystemResult insert(Equipment equipment,Long uid) {
		List<Equipment> equipmentsList = new ArrayList<Equipment>();
		List<String> snList = Arrays.asList(equipment.getSn().split(","));
		//状态
		String status = equipment.getStatus();
		//医院id
		Long h_id = equipment.getH_id();
		//科室id
		Long d_id = equipment.getD_id();
		//描述
		String description = equipment.getDescription();
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
			equipmentForInsert.setStatus(status);
			equipmentForInsert.setH_id(h_id);
			equipmentForInsert.setD_id(d_id);
			equipmentForInsert.setDescription(equipment.getDescription());
			equipmentForInsert.setDescription(description);
			equipmentForInsert.setEt_id(equipment.getEt_id());
			if (hasPropertyNo) {
				equipmentForInsert.setProperty_no(propertyNoList.get(i));
			}
			equipmentsList.add(equipmentForInsert);
		}
		equipmentMapper.insert(equipmentsList);
		equipmentTypeMapper.countIncrease(equipment.getEt_id(), snSize);
		
		//添加数据到记录表
		for (Equipment equipmentTemp : equipmentsList) {
			Equipment equipment1 = equipmentMapper.selectEquipmentIdBySn(equipmentTemp.getSn());
			EquipmentRecord equipmentRecord = new EquipmentRecord();
			equipmentRecord.setE_id(equipment1.getId());
			equipmentRecord.setChange_type(equipmentTemp.getStatus());
			equipmentRecord.setH_id(h_id);
			equipmentRecord.setD_id(d_id);
			equipmentRecord.setOperator(uid);
			equipmentRecord.setChange_time(new Date());
			equipmentRecordMapper.insert(equipmentRecord);		
		}
		return SystemResult.ok();
	}
	
	@Override
	public SystemResult selectByType(EquipmentType equipmentType) {
		List<Equipment> equipmentList = equipmentMapper.selectByType(equipmentType);
		return SystemResult.ok(equipmentList);
	}

	@Override
	public SystemResult statusChange(String id, int status, Long h_id, Long d_id,Long uid,String description) {
		List<String> ids = Arrays.asList(id.split(","));
		equipmentMapper.statusChange(ids, status, h_id, d_id,description);
		for (String idTemp : ids) {
			EquipmentRecord equipmentRecord = new EquipmentRecord();
			equipmentRecord.setE_id(Long.parseLong(idTemp));
			equipmentRecord.setChange_type(String.valueOf(status));
			equipmentRecord.setChange_time(new Date());
			equipmentRecord.setH_id(h_id);
			equipmentRecord.setD_id(d_id);
			equipmentRecord.setOperator(uid);
			equipmentRecordMapper.insert(equipmentRecord);	
		}
		return SystemResult.ok();
	}

	@Override
	public SystemResult selectEquimentExt(EquipmentExt equipmentExt) {
		List<EquipmentExt> equipmentExtList = equipmentMapper.selectEquipmentExt(equipmentExt);
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("10", "在库新机");
		statusMap.put("20", "在院");
		statusMap.put("30", "出库");
		statusMap.put("40", "借用");
		statusMap.put("50", "在库返修");
		statusMap.put("60", "审核");
		statusMap.put("70", "故障");
		for (EquipmentExt equipmentExtItem : equipmentExtList) {
			equipmentExtItem.setIn_time(equipmentExtItem.getIn_time().substring(0, 16));
			equipmentExtItem.setStatus(statusMap.get(equipmentExtItem.getStatus()));
		}
		return SystemResult.ok(equipmentExtList.size() + "", equipmentExtList);
	}

	@Override
	public Map<String, Object> selectEquimentExtByPage(HashMap<String, Object> paramMap, EquipmentExt equipmentExt) {
		List<EquipmentExt> equimentExtListByPage = equipmentMapper.selectEquipmentExtByPage(paramMap);
		Map<String, String> STATUS_CODES = new HashMap<String, String>();
		STATUS_CODES.put("10", "在库新机");
		STATUS_CODES.put("20", "在院");
		STATUS_CODES.put("30", "出库");
		STATUS_CODES.put("40", "借用");
		STATUS_CODES.put("50", "在库返修");
		STATUS_CODES.put("60", "审核");
		STATUS_CODES.put("70", "故障");
		for (EquipmentExt ext : equimentExtListByPage) {
			ext.setIn_time(ext.getIn_time().substring(0, 16));
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
	public SystemResult updateEquimentExt(EquipmentExt equipmentExt,Long uid) {
		if (equipmentExt != null) {
			// 拿到设备id
			Long id = equipmentExt.getId();
			Long h_id = equipmentExt.getH_id();//医院id
			Long d_id = equipmentExt.getD_id();//科室id
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
			String oldType = "";
			String oldPropertyNo = "";
			Long oldHId = null;
			Long oldDId = null;
			if (selectEquiment != null && selectEquiment.size() == 1) {
				oldSn = selectEquiment.get(0).getSn();
				oldStatus = selectEquiment.get(0).getStatus();
				EquipmentType equipmentType2 = null;
				//根据id去找type
				List<EquipmentType> selectEquipmentTypeById = equipmentTypeMapper.selectEquipmentTypeById(selectEquiment.get(0));
				if(selectEquipmentTypeById.size()==1) {
					equipmentType2 = selectEquipmentTypeById.get(0);
				}
				oldType = equipmentType2.getType();
				oldHId = selectEquiment.get(0).getH_id();
				oldDId = selectEquiment.get(0).getD_id();
				oldPropertyNo = selectEquiment.get(0).getProperty_no();
			}
			if (!oldSn.equals(equipmentExt.getSn())) {
				List<String> snList = Arrays.asList(equipmentExt.getSn().split(","));
				List<Equipment> equipmentsExist = equipmentMapper.selectBySn(snList);
				List<String> duplicatedSns = new ArrayList<String>();
				if (equipmentsExist != null && equipmentsExist.size() != 0) {
					for (Equipment equipentTemp : equipmentsExist) {
						duplicatedSns.add(equipentTemp.getSn());
					}
					return SystemResult.build(500, "序列号" + duplicatedSns.toString() + "已存在，请修改序列号！");
				}
				equipmentMapper.updateEquipmentExt(equipmentExt);
			}else if(!oldStatus.equals(equipmentExt.getStatus())) {
				if(oldStatus.equals("20")&&!equipmentExt.getStatus().equals("20")) {
					equipmentExt.setH_id(null);
					equipmentExt.setD_id(null);
					h_id = null;
					d_id = null;
				}
				equipmentMapper.updateEquipmentExt(equipmentExt);
				//当设备状态修改的时候,往记录表中插入数据
				EquipmentRecord equipmentRecord = new EquipmentRecord();
				equipmentRecord.setE_id(id);
				equipmentRecord.setChange_type(String.valueOf(equipmentExt.getStatus()));
				equipmentRecord.setChange_time(new Date());
				equipmentRecord.setH_id(h_id);
				equipmentRecord.setD_id(d_id);
				equipmentRecord.setOperator(uid);
				equipmentRecordMapper.insert(equipmentRecord);
			}else if(!oldType.equals(equipmentExt.getType())) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
			}else if(oldHId != null && h_id != null) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
				//当设备状态修改的时候,往记录表中插入数据
				EquipmentRecord equipmentRecord = new EquipmentRecord();
				equipmentRecord.setE_id(id);
				equipmentRecord.setChange_type(String.valueOf(equipmentExt.getStatus()));
				equipmentRecord.setChange_time(new Date());
				equipmentRecord.setH_id(h_id);
				equipmentRecord.setD_id(d_id);
				equipmentRecord.setOperator(uid);
				equipmentRecordMapper.insert(equipmentRecord);
			}else if(oldHId!=null && !oldHId.equals(h_id)) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
				//当设备状态修改的时候,往记录表中插入数据
				EquipmentRecord equipmentRecord = new EquipmentRecord();
				equipmentRecord.setE_id(id);
				equipmentRecord.setChange_type(String.valueOf(equipmentExt.getStatus()));
				equipmentRecord.setChange_time(new Date());
				equipmentRecord.setH_id(h_id);
				equipmentRecord.setD_id(d_id);
				equipmentRecord.setOperator(uid);
				equipmentRecordMapper.insert(equipmentRecord);
			}else if(oldDId == null && d_id != null) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
				//当设备状态修改的时候,往记录表中插入数据
				EquipmentRecord equipmentRecord = new EquipmentRecord();
				equipmentRecord.setE_id(id);
				equipmentRecord.setChange_type(String.valueOf(equipmentExt.getStatus()));
				equipmentRecord.setChange_time(new Date());
				equipmentRecord.setH_id(h_id);
				equipmentRecord.setD_id(d_id);
				equipmentRecord.setOperator(uid);
				equipmentRecordMapper.insert(equipmentRecord);
			}else if(oldDId!=null && !oldDId.equals(d_id)) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
				//当设备状态修改的时候,往记录表中插入数据
				EquipmentRecord equipmentRecord = new EquipmentRecord();
				equipmentRecord.setE_id(id);
				equipmentRecord.setChange_type(String.valueOf(equipmentExt.getStatus()));
				equipmentRecord.setChange_time(new Date());
				equipmentRecord.setH_id(h_id);
				equipmentRecord.setD_id(d_id);
				equipmentRecord.setOperator(uid);
				equipmentRecordMapper.insert(equipmentRecord);
			}else if(oldPropertyNo == null) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
			}else if(!oldPropertyNo.equals(equipmentExt.getProperty_no())) {
				equipmentMapper.updateEquipmentExt(equipmentExt);
			}else if(oldSn.equals(equipmentExt.getSn())){
				return SystemResult.build(500, "没有修改任何内容！");
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
		
		// 查询在库760
		Integer meter_760_10 = equipmentMapper.selectCountByStatus("760", 10, "700Pro") 
				+ equipmentMapper.selectCountByStatus("760", 50, "700Pro");
		// 查询在库760新机
		Integer new_760_10 = equipmentMapper.selectCountByStatus("760", 10, "700Pro");		
		// 查询在库760返修机
		Integer fanxiu_760_50 = equipmentMapper.selectCountByStatus("760", 50, "700Pro");
		// 查询在院760meter
		Integer meter_760_20 = equipmentMapper.selectCountByStatus("760", 20, "700Pro");
		
		// 查询在库旧761
		Integer old_total_761_10 = equipmentMapper.selectCountByStatus("旧761", 10, "700Pro")
				+ equipmentMapper.selectCountByStatus("旧761", 50, "700Pro");
		// 查询在库旧761新机
		Integer old_761_10 = equipmentMapper.selectCountByStatus("旧761", 10, "700Pro");
		// 查询在库旧761返修机
		Integer old_761_50 = equipmentMapper.selectCountByStatus("旧761", 50, "700Pro");
		// 查询在院旧761meter
		Integer old_761_20 = equipmentMapper.selectCountByStatus("旧761", 20, "700Pro");
		
		// 查询在库761
		Integer meter_total_761_10 = equipmentMapper.selectCountByStatus("761", 10, "700Pro")
				+ equipmentMapper.selectCountByStatus("761", 50, "700Pro");
		// 查询在库761新机
		Integer meter_761_10 = equipmentMapper.selectCountByStatus("761", 10, "700Pro");
		// 查询在库761返修机
		Integer meter_761_50 = equipmentMapper.selectCountByStatus("761", 50, "700Pro");
		// 查询在院761meter
		Integer meter_761_20 = equipmentMapper.selectCountByStatus("761", 20, "700Pro");
		

		// 查询在库meter
		Integer meter_10 = equipmentMapper.selectCountByStatus("", 10, "700Pro");
		// 查询在院meter
		Integer meter_20 = equipmentMapper.selectCountByStatus("", 20, "700Pro");
		// 查询出库meter
		Integer meter_30 = equipmentMapper.selectCountByStatus("", 30, "700Pro");
		// 查询借用meter
		Integer meter_40 = equipmentMapper.selectCountByStatus("", 40, "700Pro");
		// 查询返修meter
		Integer meter_50 = equipmentMapper.selectCountByStatus("", 50, "700Pro");
		// 查询审核meter
		Integer meter_60 = equipmentMapper.selectCountByStatus("", 60, "700Pro");
		// 查询故障meter
		Integer meter_70 = equipmentMapper.selectCountByStatus("", 70, "700Pro");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		map.put("meter_760_10", meter_760_10);
		map.put("new_760_10", new_760_10);
		map.put("fanxiu_760_50", fanxiu_760_50);
		map.put("meter_760_20", meter_760_20);
		
		map.put("old_total_761_10", old_total_761_10);
		map.put("old_761_10", old_761_10);
		map.put("old_761_50", old_761_50);
		map.put("old_761_20", old_761_20);
		
		map.put("meter_total_761_10", meter_total_761_10);
		map.put("meter_761_10", meter_761_10);
		map.put("meter_761_50", meter_761_50);
		map.put("meter_761_20", meter_761_20);
		
		
		map.put("meter_10", meter_10);
		map.put("meter_20", meter_20);
		map.put("meter_30", meter_30);
		map.put("meter_40", meter_40);
		map.put("meter_50", meter_50);
		map.put("meter_60", meter_60);
		map.put("meter_70", meter_70);
		return map;
	}

	@Override
	public Equipment selectEquipmentIdBySn(String sn) {
		Equipment equipment = equipmentMapper.selectEquipmentIdBySn(sn);
		return equipment;
	}

	@Override
	public Map<String, String> selectHospitalDetail(Long id) {
		HashMap<String, String> map = new HashMap<String, String>();
		List<Equipment> equiments = equipmentMapper.selectEquipmentById(id);//根据设备id查询设备
		if(equiments != null && equiments.size() == 1) {
			//获取到设备状态
			String status = equiments.get(0).getStatus();
			map.put("status", status);
			Long h_id = equiments.get(0).getH_id();//查询到医院id
			String hNo = String.valueOf(h_id);
			List<Hospital> hospitals = hospitalMapper.selectHospitalById(h_id);
			if(hospitals != null && hospitals.size() == 1) {
				String province = hospitals.get(0).getProvince();//省份
				map.put("province", province);
				map.put("hNo", hNo);
			}
			Long d_id = equiments.get(0).getD_id();//查询到科室id
			String dNo = String.valueOf(d_id);
			map.put("dNo", dNo);
			/*List<Department> departments = departmentMapper.selectDepartmentById(d_id);
			if(departments != null && departments.size() == 1) {
				String hospital = hospitals.get(0).getName();//医院
				map.put("hospital", hospital);
			}*/
		}
		return map;
	}

	@Override
	public List<Equipment> selectEquipmentIdByDid(Long d_id) {
		List<Equipment> selectEquipmentIdByDid = equipmentMapper.selectEquipmentIdByDid(d_id);
		return selectEquipmentIdByDid;
	}

	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom(from);//发件人Email地址
	    message.setTo(to);//收件人Email地址
	    message.setSubject(subject);//邮件主题
	    message.setText(content);//邮件内容
	    try {
	        mailSender.send(message);
	        logger.info("邮件已经发送。");
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.error("发送邮件时发生异常！", e);
	    }
	}

	@Override
	public List<Equipment> selectBySn(List<String> Sns) {
		List<Equipment> selectBySn = equipmentMapper.selectBySn(Sns);
		return selectBySn;
	}

	@Override
	public List<Equipment> selectById(List<String> ids) {
		List<Equipment> selectById = equipmentMapper.selectById(ids);
		return selectById;
	}
}
