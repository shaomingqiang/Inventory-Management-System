package com.bionime.service;

import java.util.List;

import com.bionime.pojo.Hospital;
import com.bionime.utils.SystemResult;

public interface HospitalService {
	SystemResult insert(Hospital hospital);
	SystemResult selectByProvince(Hospital hospital);
}
