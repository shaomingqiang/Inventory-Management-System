package com.bionime.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.User;
import com.bionime.service.UserService;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午11:19:26
 * 
 */
@RestController
@MapperScan(basePackages="com.bionime.mapper")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("getAll")
	public List<User> getAll() {
		List<User> list = userService.findAll();
		return list;
	}
}
