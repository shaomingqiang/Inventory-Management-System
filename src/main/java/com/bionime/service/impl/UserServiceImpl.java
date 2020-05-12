package com.bionime.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.UserMapper;
import com.bionime.pojo.User;
import com.bionime.service.UserService;
import com.bionime.utils.SystemResult;

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
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void insert(User user) {
		userMapper.insert(user);

	}

	@Override
	public SystemResult login(User user) {
		User userLogin = userMapper.login(user);
		String msg = null;
		if(userLogin==null){
			msg="用户名或密码错误，请重新输入！";
			return SystemResult.ok(msg);
		}		
		return SystemResult.ok(userLogin);
	}
	

	@Override
	public SystemResult findAllUsers() {
		List<User> findAllUsers = userMapper.findAllUsers();
		String msg = null;
		if(findAllUsers==null){
			msg="未查询到用户";
			return SystemResult.ok(msg);
		}		
		return SystemResult.ok(findAllUsers);
	}

	@Override
	public User selectUserByUserName(User user) {
		User userLogin = userMapper.login(user);
		return userLogin;
	}
}
