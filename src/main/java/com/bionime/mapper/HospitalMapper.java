package com.bionime.mapper;

import java.util.HashMap;
import java.util.List;

import com.bionime.pojo.Department;
import com.bionime.pojo.Hospital;

public interface HospitalMapper {
	int insert(Hospital hospital);
	List<Hospital> selectHospitalExt(Hospital hospital);
	//分页查询医院统计信息
	List<Hospital> selectHospitalExtByPage(HashMap<String, Object> map); 
	//根据id查询医院
	List<Hospital> selectHospitalById(Long id);
	//根据id查询科室
	List<Department> selectDepartment(Long id);
	
}
