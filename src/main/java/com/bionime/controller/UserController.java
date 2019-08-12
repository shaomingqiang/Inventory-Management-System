package com.bionime.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.User;
import com.bionime.service.UserService;
import com.bionime.utils.SystemResult;

/**
 * 
 * <p>
 * Title: UserController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.bionime.com
 * </p>
 * 
 * @author Ethan.Shao
 * @date 2019年7月11日下午3:30:55
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户添加
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param user
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody User user) {
		userService.insert(user);
	}

	/**
	 * 用户登录
	 * <p>
	 * Title: login
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public SystemResult login(@RequestBody User user, HttpSession session) {
		SystemResult result = userService.login(user);
		if (user != null) {
			session.setAttribute("user", user);
		}
		return result;
	}

	// 取session
	@RequestMapping(value = "/getSession", method = RequestMethod.GET)
	public User getSession(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}
}
