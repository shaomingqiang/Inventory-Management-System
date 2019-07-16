package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.ReworkMapper;
import com.bionime.pojo.Rework;
import com.bionime.service.ReworkService;

/**
 * 
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:57:14
 * @version 1.0
 */
@Transactional
@Service
public class ReworkServiceImpl implements ReworkService {

	@Autowired
	private ReworkMapper reworkMapper;
	
	@Override
	public void insert(Rework rework) {
		reworkMapper.insert(rework);

	}

}
