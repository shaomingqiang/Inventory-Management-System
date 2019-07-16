package com.bionime.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.DepartmentMapper;
import com.bionime.pojo.Department;
import com.bionime.pojo.DepartmentExt;
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
	public void insert(Department department) {
		departmentMapper.insert(department);

	}

	@Override
	public SystemResult findDeptAndHosp() {
		List<DepartmentExt> list = departmentMapper.findDeptAndHosp();
		return SystemResult.ok(list);
	}

	@Override
	public SystemResult findDeptAndHosp1() {
		List<DepartmentExt> list = departmentMapper.findDeptAndHosp1();
		return SystemResult.ok(list);
	}

}
