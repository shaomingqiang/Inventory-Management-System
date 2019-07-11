package com.bionime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.Hospital;
import com.bionime.pojo.User;
import com.bionime.service.HospitalService;
import com.bionime.service.UserService;



/**
 * 
 * <p>Title: HospitalController</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:30:44
 * @version 1.0
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public void insert(@RequestBody Hospital hospital) {
		hospitalService.insert(hospital);
	}
}
