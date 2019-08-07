package com.bionime.mapper;

import java.util.List;
import com.bionime.pojo.Hospital;


public interface HospitalMapper {
	int insert(Hospital hospital);
	List<Hospital> selectByName(String name);
	List<Hospital> selectByProvince(String province);
	int updateHospitalById(Hospital hospital);
}
