package com.bionime.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.EquipmentMapper;
import com.bionime.pojo.Equipment;
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
@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public SystemResult insert(Equipment equipment) {
		equipment.setIn_time(new Date());
		equipmentMapper.insert(equipment);
		return SystemResult.ok();
	}

}
