package com.bionime.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.HospitalMapper;
import com.bionime.pojo.Hospital;
import com.bionime.service.HospitalService;
import com.bionime.utils.SystemResult;


/**
 * 
 * <p>Title: HospitalServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:31:33
 * @version 1.0
 */
@Transactional
@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalMapper hospitalMapper;
	
	@Override
	public SystemResult insert(Hospital hospital) {
		hospitalMapper.insert(hospital);
		return SystemResult.ok();

	}

	@Override
	public SystemResult selectByProvince(Hospital hospital) {
		List<Hospital> result = hospitalMapper.selectByProvince(hospital);
		return SystemResult.ok(result);
	}

}
