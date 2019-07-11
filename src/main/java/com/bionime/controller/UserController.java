package com.bionime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.bionime.pojo.User;
import com.bionime.service.UserService;



/**
 * 
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:30:55
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public void insert(@RequestBody User user) {
		userService.insert(user);
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public boolean login(@RequestBody User user) {
		User user1 = userService.login(user);
		if(user1!=null){
			return true;
		}else{
			return false;
		}
	}
}
