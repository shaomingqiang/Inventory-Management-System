package com.bionime.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.DepartmentMapper;
import com.bionime.pojo.Department;
import com.bionime.pojo.DepartmentExt;
import com.bionime.pojo.Hospital;
import com.bionime.service.DepartmentService;
import com.bionime.utils.SystemResult;

/**
 * 
 * <p>Title: DepartmentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:40:09
 * @version 1.0
 */
@Transactional
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public SystemResult insert(Department department) {
		if(departmentMapper.findDept(department)!=null&&departmentMapper.findDept(department).size()>0){
			return SystemResult.build(500, "科室已存在，请重新录入！");
		}
		departmentMapper.insert(department);
		return SystemResult.ok();
	}

	@Override
	public SystemResult findDeptAndHosp() {
		List<DepartmentExt> list = departmentMapper.findDeptAndHosp();
		return SystemResult.ok(list);
	}

	@Override
	public SystemResult findDeptAndHosp1(Department department) {
		List<DepartmentExt> list = departmentMapper.findDeptAndHosp1(department);
		Map<String,Long> typeMap = new HashMap<String,Long>();
		for (DepartmentExt departmentExt : list) {
			typeMap.put(departmentExt.getName(), departmentExt.getId());
		}
		SystemResult result = SystemResult.ok(typeMap);
		return result;
	}
	
	@Override
	public SystemResult findDept(Department department) {
		List<Department> list = departmentMapper.findDept(department);
		Map<String,Long> typeMap = new HashMap<String,Long>();
		for (Department department1 : list) {
			typeMap.put(department1.getName(), department1.getId());
		}
		SystemResult result = SystemResult.ok(typeMap);
		return result;
	}

}
