package com.bionime.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月12日 下午2:46:18 控制页面跳转
 */
@Controller
public class PageController {
	// 页面跳转
	@RequestMapping(value = "/toPage", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		String url = request.getParameter("url");
		return url;
	}
}
