package com.bionime.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.UserMapper;
import com.bionime.pojo.User;
import com.bionime.service.UserService;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午11:15:25 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> findAll() {
		List<User> userList = userMapper.findAll();
		if (userList != null) {
			return userList;
		} else {
			return null;
		}
	}

}
