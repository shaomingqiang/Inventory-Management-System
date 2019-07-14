package com.bionime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.bionime.pojo.EquipmentType;
import com.bionime.service.EquipmentTypeService;
import com.bionime.utils.SystemResult;

/**
 *
 * <p>Title: EquipmentTypeController</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:26:10
 * @version 1.0
 */
@RestController
@RequestMapping("/equipmentType")
public class EquipmentTypeController {
	@Autowired
	private EquipmentTypeService equipmentTypeService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public SystemResult insertEquipment(@RequestBody EquipmentType equipmentType) {
		SystemResult result = equipmentTypeService.insert(equipmentType);
		return result;
	}
}
