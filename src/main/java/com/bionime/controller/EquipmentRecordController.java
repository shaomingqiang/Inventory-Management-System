package com.bionime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentRecord;
import com.bionime.pojo.User;
import com.bionime.service.EquipmentRecordService;
import com.bionime.service.EquipmentService;

/**
 * 
 * <p>Title: EquipmentRecordController</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:31:04
 * @version 1.0
 */
@RestController
@RequestMapping("/equipmentRecord")
public class EquipmentRecordController {
	@Autowired
	private EquipmentRecordService equipmentRecordService;

	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public void insertEquipment(@RequestBody EquipmentRecord equipmentRecord) {
		equipmentRecordService.insert(equipmentRecord);
	}
}