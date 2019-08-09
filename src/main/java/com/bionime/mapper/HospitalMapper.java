package com.bionime.mapper;

import java.util.List;

import com.bionime.pojo.Hospital;

public interface HospitalMapper {
	int insert(Hospital hospital);
	List<Hospital> selectByProvince(Hospital hospital);
}
