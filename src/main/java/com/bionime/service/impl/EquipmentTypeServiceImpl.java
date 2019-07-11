package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.EquipmentTypeMapper;
import com.bionime.pojo.EquipmentType;
import com.bionime.service.EquipmentTypeService;

/**
 * 
 * <p>Title: EquipmentTypeServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:30:19
 * @version 1.0
 */
@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
	
	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;
	
	@Override
	public void insert(EquipmentType equipmentType) {
		equipmentTypeMapper.insert(equipmentType);
	}

}