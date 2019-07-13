package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if(userLogin!=null){
			msg = "登录成功！";
		}else{
			msg = "用户名不存在或密码错误！";
		}
		return SystemResult.ok(msg);
	}

}
