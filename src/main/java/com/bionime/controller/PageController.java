package com.bionime.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String toPage(HttpServletRequest request) {
		String url = request.getParameter("url");
		
		//打jar包以后路径要进行修改，本地测试可以注释掉下面一段代码
		//开始
		
		  String head = url.substring(0,1); if("/".equals(head)) { url =
		  url.substring(1, url.length()); }
		 
		//结束
		
		//如果返回登录页面,就注销session
		if("login".equals(url)) {
			request.getSession().removeAttribute("user");
		}
		return url;
	}
}
