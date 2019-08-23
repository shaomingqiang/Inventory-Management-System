package com.bionime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.Department;
import com.bionime.service.DepartmentService;
import com.bionime.utils.SystemResult;



/**
 * 
 * <p>Title: DepartmentController</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:42:35
 * @version 1.0
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 科室添加
	 * <p>Title: insert</p>
	 * <p>Description: </p>
	 * @param department
	 */
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public SystemResult insert(@RequestBody Department department) {
		SystemResult result = departmentService.insert(department);
		return result;
		
	}
	
	@RequestMapping(value="/findDeptAndHosp",method = RequestMethod.POST)
	public SystemResult findDeptAndHosp(@RequestBody Department department) {
		SystemResult result = departmentService.findDeptAndHosp();
		return result;
	}
	
	@RequestMapping(value="/findDeptAndHosp1",method = RequestMethod.POST)
	public SystemResult findDeptAndHosp1(@RequestBody Department department) {
		SystemResult result = departmentService.findDeptAndHosp1(department);
		return result;
	}
	
	@RequestMapping(value="/findDept",method = RequestMethod.POST)
	public SystemResult findDept(@RequestBody Department department) {
		SystemResult result = departmentService.findDept(department);
		return result;
	}
}
