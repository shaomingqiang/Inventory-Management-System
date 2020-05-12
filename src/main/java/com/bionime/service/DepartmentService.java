package com.bionime.service;

import java.util.HashMap;
import java.util.Map;

import com.bionime.pojo.Department;
import com.bionime.pojo.DepartmentDetail;
import com.bionime.utils.SystemResult;

public interface DepartmentService {
	SystemResult insert(Department department);
	SystemResult findDept(Department department);
	SystemResult findDeptAndHosp();
	SystemResult findDeptAndHosp1(Department department);
	SystemResult selectDepartmentById(Long id);
	Map<String, Object> selectDepartmentDetailByPage(HashMap<String, Object> map,DepartmentDetail departmentDetail);
	Map<String, Object> selectDepartmentByPage(HashMap<String, Object> paramMap,DepartmentDetail departmentDetail);
}
