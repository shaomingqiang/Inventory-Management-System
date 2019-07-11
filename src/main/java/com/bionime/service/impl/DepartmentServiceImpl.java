package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.DepartmentMapper;
import com.bionime.mapper.UserMapper;
import com.bionime.pojo.Department;
import com.bionime.pojo.Equipment;
import com.bionime.pojo.User;
import com.bionime.service.DepartmentService;
import com.bionime.service.UserService;

/**
 * 
 * <p>Title: DepartmentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:40:09
 * @version 1.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public void insert(Department department) {
		departmentMapper.insert(department);

	}

}
