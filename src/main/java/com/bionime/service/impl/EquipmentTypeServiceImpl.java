package com.bionime.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.EquipmentTypeMapper;
import com.bionime.pojo.EquipmentType;
import com.bionime.service.EquipmentTypeService;
import com.bionime.utils.SystemResult;

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
	public SystemResult insert(EquipmentType equipmentType) {
		equipmentTypeMapper.insert(equipmentType);
		return SystemResult.ok();
	}


	@Override
	public SystemResult selectDistinctEquipmentType() {
		List<String> distinctEquipmentTypeList = equipmentTypeMapper.selectDistinctEquipmentType();
		return SystemResult.ok(distinctEquipmentTypeList);
	}


	@Override
	public SystemResult selectEquipmentTypeByName(EquipmentType equipmentType) {
		List<EquipmentType> EquipmentTypeList = equipmentTypeMapper.selectEquipmentTypeByName(equipmentType);
		SystemResult result = SystemResult.ok(EquipmentTypeList);
		return result;
	}

}
