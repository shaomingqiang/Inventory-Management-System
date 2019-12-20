package com.bionime.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.EquipmentExt;
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
	@RequestMapping(value="/selectHospital",method = {RequestMethod.GET,RequestMethod.POST})
	public SystemResult selectHospital(@RequestBody Hospital hospital) {
		SystemResult result = hospitalService.selectHospital(hospital);
		return result;
	}
	
	/**
	 * 根据医院查询科室
	 * <p>Title: insert</p>
	 * <p>Description: </p>
	 * @param hNo
	 * @return
	 */
	@RequestMapping(value="/selectDepartment",method = {RequestMethod.GET,RequestMethod.POST})
	public SystemResult selectDepartment(@RequestBody Hospital hospital) {
		Long hNo = hospital.getId();
		SystemResult result = hospitalService.selectDepartmentByHospital(hNo);
		return result;
	}
	
	
	//分页查询加模糊查询
	@RequestMapping(value = "/selectHospitalExtByPage", method = RequestMethod.GET)
	public Object selectHospital(HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String name = request.getParameter("name");
		String province = request.getParameter("province");
		int temp = limit;
		if (page != 1) {
			page = (page - 1) * temp;
		} else {
			page = 0;
		}
		System.out.println(name);
		System.out.println(province);
		paramMap.put("page", page);
		paramMap.put("limit", limit);
		paramMap.put("name", name);
		paramMap.put("province", province);
		paramMap.put("tname", "血糖仪");
		HashMap<String, Object> result = (HashMap<String, Object>) hospitalService.selectHospitalExtByPage(paramMap);
		result.put("msg", "");
		return result;
	}
	
	@RequestMapping(value="/selectHospitalByProvince",method = RequestMethod.POST)
	public SystemResult selectHospitalByProvince(@RequestBody Hospital hospital) {
		SystemResult result = hospitalService.selectHospitalByProvince(hospital);
		return result;
	}
	
	@RequestMapping(value = "/updateHospital", method = RequestMethod.POST)
	public SystemResult updateEquiment(@RequestBody Hospital hospital) {
		SystemResult result = hospitalService.updateHospital(hospital);
		return result;
	}
}
