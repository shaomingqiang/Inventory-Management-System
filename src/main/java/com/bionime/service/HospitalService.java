package com.bionime.service;

import java.util.HashMap;
import java.util.Map;

import com.bionime.pojo.Hospital;
import com.bionime.utils.SystemResult;

public interface HospitalService {
	SystemResult insert(Hospital hospital);
	SystemResult selectHospital(Hospital hospital);
	SystemResult selectHospitalByProvince(Hospital hospital);
	SystemResult selectDepartmentByHospital(Long id);
	SystemResult selectHospitalById(Long id);
	Map<String, Object> selectHospitalExtByPage(HashMap<String, Object> hospitalMap);
	SystemResult updateHospital(Hospital hospital);
	SystemResult getProvinceData();
}
