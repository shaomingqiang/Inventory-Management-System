package com.bionime.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bionime.pojo.Department;
import com.bionime.pojo.Hospital;
import com.bionime.pojo.HospitalExt;
import com.bionime.pojo.Province;

public interface HospitalMapper {
	int insert(Hospital hospital);
	List<Hospital> selectHospitalExt(Hospital hospital);
	//分页查询医院统计信息
	List<HospitalExt> selectHospitalExtByPage(HashMap<String, Object> map); 
	//根据id查询医院
	List<Hospital> selectHospitalById(Long id);
	//根据id查询科室
	List<Department> selectDepartment(Long id);
	//修改医院信息
	int updateHospital(Hospital hospital);
	//查詢每家醫院的科室數量
	List<HospitalExt>  selectHospitalExtDCount();
	//查詢每個省份的醫院數量
	List<Province> getProvinceData();
}
