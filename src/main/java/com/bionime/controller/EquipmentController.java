package com.bionime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentType;
import com.bionime.service.EquipmentService;
import com.bionime.utils.SystemResult;

/**
 * 
 * <p>Title: EquipmentController</p>
 * <p>Description:</p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:25:34
 * @version 1.0
 */
@RestController
@RequestMapping("/equipment")
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;

	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public SystemResult insertEquipment(@RequestBody Equipment equipment) {
		equipment.setEt_id(1L);
		SystemResult result = equipmentService.insert(equipment);
		return result;
	}
	
	@RequestMapping(value="/selectByType",method = RequestMethod.POST)
	public SystemResult insertEquipment(@RequestBody EquipmentType equipmentType) {
		SystemResult result = equipmentService.selectByType(equipmentType);
		return result;
	}
}
