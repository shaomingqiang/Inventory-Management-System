package com.bionime.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bionime.mapper.DepartmentMapper;
import com.bionime.mapper.EquipmentMapper;
import com.bionime.pojo.DepartmentExt;

public class Test {
	@Autowired
	private DepartmentMapper departmentMapper;
	
	
	public void find(){
		System.out.println("11");
		List<DepartmentExt> list = departmentMapper.findDeptAndHosp();
		System.out.println("11");
	}
}
