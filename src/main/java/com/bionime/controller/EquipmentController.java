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
import com.bionime.pojo.User;
import com.bionime.service.EquipmentService;

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
	public void insertEquipment(@RequestBody Equipment equipment) {
		equipmentService.insert(equipment);
	}
}