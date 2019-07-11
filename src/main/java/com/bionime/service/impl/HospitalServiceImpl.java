package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.HospitalMapper;
import com.bionime.mapper.UserMapper;
import com.bionime.pojo.Equipment;
import com.bionime.pojo.Hospital;
import com.bionime.pojo.User;
import com.bionime.service.HospitalService;
import com.bionime.service.UserService;

/**
 * 
 * <p>Title: HospitalServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:31:33
 * @version 1.0
 */
@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalMapper hospitalMapper;
	
	@Override
	public void insert(Hospital hospital) {
		hospitalMapper.insert(hospital);

	}

}
