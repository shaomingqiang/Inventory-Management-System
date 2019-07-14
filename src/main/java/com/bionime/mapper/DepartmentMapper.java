package com.bionime.mapper;

import java.util.List;

import com.bionime.pojo.Department;
import com.bionime.pojo.DepartmentExt;

public interface DepartmentMapper {
	int insert(Department department);
	List<DepartmentExt> findDeptAndHosp();
	List<DepartmentExt> findDeptAndHosp1();
}
