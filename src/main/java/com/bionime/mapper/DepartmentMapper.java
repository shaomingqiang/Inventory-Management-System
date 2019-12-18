package com.bionime.mapper;

import java.util.HashMap;
import java.util.List;

import com.bionime.pojo.Department;
import com.bionime.pojo.DepartmentDetail;
import com.bionime.pojo.DepartmentExt;

public interface DepartmentMapper {
	int insert(Department department);
	List<Department> findDept(Department department);
	List<DepartmentExt> findDeptAndHosp();
	List<DepartmentExt> findDeptAndHosp1(Department department);
	List<DepartmentDetail> selectDepartmentDetail(DepartmentDetail departmentDetail);
	List<DepartmentDetail> selectDepartmentDetailByPage(HashMap<String, Object> map);
	List<Department> selectDepartmentById(Long id);
}
