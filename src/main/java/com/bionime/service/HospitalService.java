package com.bionime.service;

import java.util.HashMap;
import java.util.Map;

import com.bionime.pojo.Hospital;
import com.bionime.utils.SystemResult;

public interface HospitalService {
	SystemResult insert(Hospital hospital);
	SystemResult selectHospital(Hospital hospital);
	SystemResult selectHospitalByProvince(Hospital hospital);
	Map<String, Object> selectHospitalExtByPage(HashMap<String, Object> hospitalMap);
}
