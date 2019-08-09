package com.bionime.service;

import com.bionime.pojo.Department;
import com.bionime.utils.SystemResult;

public interface DepartmentService {
	SystemResult insert(Department department);
	SystemResult findDeptAndHosp();
	SystemResult findDeptAndHosp1();
}
