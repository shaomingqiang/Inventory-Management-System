package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.EquipmentMapper;
import com.bionime.pojo.Equipment;
import com.bionime.service.EquipmentService;

/**
 * 
 * <p>Title: EquipmentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:30:10
 * @version 1.0
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public void  insert(Equipment equipment) {
		equipmentMapper.insert(equipment);
	}

}
