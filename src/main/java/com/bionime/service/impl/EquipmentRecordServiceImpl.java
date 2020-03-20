package com.bionime.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.EquipmentRecordMapper;
import com.bionime.mapper.UserMapper;
import com.bionime.pojo.Department;
import com.bionime.pojo.EquipmentRecord;
import com.bionime.pojo.User;
import com.bionime.service.EquipmentRecordService;
import com.bionime.service.UserService;
import com.bionime.utils.SystemResult;

/**
 * 
 * <p>Title: EquipmentRecordServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年9月24日下午2:28:38
 * @version 1.0
 */
@Transactional
@Service
public class EquipmentRecordServiceImpl implements EquipmentRecordService{

	@Autowired
	private EquipmentRecordMapper equipmentRecordMapper;

	@Override
	public SystemResult insert(EquipmentRecord equipmentRecord) {
		equipmentRecordMapper.insert(equipmentRecord);
		return SystemResult.ok();
	}
	
	@Override
	public Map<String, Object> findEquiomentRecordByPage(HashMap<String, Object> paramMap,EquipmentRecord equipmentRecord) {
		List<EquipmentRecord> list =  equipmentRecordMapper.findEquiomentRecordByPage(paramMap);
		List<EquipmentRecord> equipmentRecordList =  equipmentRecordMapper.findEquiomentRecord(equipmentRecord);
		int count = equipmentRecordList.size();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("data",list);
		map.put("count", count);
		return map;
	}
	
}
