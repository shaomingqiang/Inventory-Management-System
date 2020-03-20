package com.bionime.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.Department;
import com.bionime.pojo.DepartmentDetail;
import com.bionime.pojo.Equipment;
import com.bionime.service.DepartmentService;
import com.bionime.service.EquipmentService;
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
	@Autowired
	private EquipmentService equipmentService;
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
	 
	
	@RequestMapping(value="/findDeptDetail",method = RequestMethod.GET)
	public Object findDeptDetail(HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		Long h_id = Long.parseLong(request.getParameter("h_id"));
		int temp = limit;
		if (page != 1) {
			page = (page - 1) * temp;
		} else {
			page = 0;
		}
		DepartmentDetail departmentDetail = new DepartmentDetail();
		departmentDetail.setH_id(h_id);
		paramMap.put("page", page);
		paramMap.put("limit", limit);
		paramMap.put("h_id", h_id);
		HashMap<String, Object> result = (HashMap<String, Object>) departmentService.selectDepartmentDetailByPage(paramMap,
				departmentDetail);
		result.put("msg", "");
		return result;
	}
	
	@RequestMapping(value="/findDept",method = RequestMethod.GET)
	public Object findDept(HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		Long h_id = Long.parseLong(request.getParameter("h_id"));
		int temp = limit;
		if (page != 1) {
			page = (page - 1) * temp;
		} else {
			page = 0;
		}
		DepartmentDetail departmentDetail = new DepartmentDetail();
		departmentDetail.setH_id(h_id);
		paramMap.put("page", page);
		paramMap.put("limit", limit);
		paramMap.put("h_id", h_id);
		HashMap<String, Object> result = (HashMap<String, Object>) departmentService.selectDepartmentByPage(paramMap,
				departmentDetail);
		List<DepartmentDetail> departmentDetailList = (ArrayList<DepartmentDetail>)result.get("data");
		Long d_id = null;
		for (DepartmentDetail departmentDetail2 : departmentDetailList) {
			d_id = departmentDetail2.getD_id();
			List<Equipment> selectEquipmentIdByDid = equipmentService.selectEquipmentIdByDid(d_id);
			if(selectEquipmentIdByDid == null||selectEquipmentIdByDid.size()==0) {
				departmentDetail2.setStatus("停用");
			}else {
				departmentDetail2.setStatus("启用");
			}
		}
		result.put("msg", "");
		return result;
	}
}
