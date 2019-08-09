package com.bionime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.Hospital;
import com.bionime.service.HospitalService;
import com.bionime.utils.SystemResult;


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
	
	/**
	 * 医院添加
	 * <p>Title: insert</p>
	 * <p>Description: </p>
	 * @param hospital
	 */
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public SystemResult insert(@RequestBody Hospital hospital) {
		SystemResult result = hospitalService.insert(hospital);
		return result;
	}
	
	/**
	 * 根据省份查询医院
	 * <p>Title: insert</p>
	 * <p>Description: </p>
	 * @param hospital
	 * @return
	 */
	@RequestMapping(value="/selectByProvince",method = RequestMethod.POST)
	public SystemResult selectByProvince(@RequestBody Hospital hospital) {
		SystemResult result = hospitalService.selectByProvince(hospital);
		return result;
	}
}
