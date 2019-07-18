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

	/**
	 * 设备类型添加
	 * <p>Title: insertEquipment</p>
	 * <p>Description: </p>
	 * @param equipmentType
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public SystemResult insertEquipment(@RequestBody EquipmentType equipmentType) {
		SystemResult result = equipmentTypeService.insert(equipmentType);
		return result;
	}
	
	/**
	 * 列举所有的设备类型
	 * <p>Title: insertEquipment</p>
	 * <p>Description: </p>
	 * @return
	 */
	@RequestMapping(value = "/selectDistinctEquipmentType", method = RequestMethod.GET)
	public SystemResult insertEquipment() {
		SystemResult result = equipmentTypeService.selectDistinctEquipmentType();
		return result;
	}
	
	/**
	 * 根据类型名查找型号
	 * <p>Title: selectEquipmentTypeByName</p>
	 * <p>Description: </p>
	 * @param equipmentType
	 * @return
	 */
	@RequestMapping(value = "/selectByName", method = RequestMethod.POST)
	public SystemResult selectEquipmentTypeByName(@RequestBody EquipmentType equipmentType) {
		SystemResult result = equipmentTypeService.selectByName(equipmentType);
		return result;
	}
	
	
}
