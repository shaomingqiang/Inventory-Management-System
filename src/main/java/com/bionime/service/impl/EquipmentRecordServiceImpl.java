package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.EquipmentRecordMapper;
import com.bionime.pojo.EquipmentRecord;
import com.bionime.service.EquipmentRecordService;

/**
 * 
 * <p>Title: EquipmentRecordServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:38:31
 * @version 1.0
 */
@Transactional
@Service
public class EquipmentRecordServiceImpl implements EquipmentRecordService {

	@Autowired
	private EquipmentRecordMapper equipmentRecordMapper;

	@Override
	public void  insert(EquipmentRecord equipmentRecord) {
		equipmentRecordMapper.insert(equipmentRecord);
	}

}
