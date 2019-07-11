package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.UserMapper;
import com.bionime.pojo.Equipment;
import com.bionime.pojo.User;
import com.bionime.service.UserService;

/**
 * 
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:57:14
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void insert(User user) {
		userMapper.insert(user);

	}

	@Override
	public User login(User user) {
		User user1 = userMapper.login(user);
		return user1;
	}

}
