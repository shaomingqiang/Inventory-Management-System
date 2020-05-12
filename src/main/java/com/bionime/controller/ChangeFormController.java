package com.bionime.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.ChangeFormExt;
import com.bionime.service.ChangeFormService;
import com.bionime.utils.SystemResult;

/**
 * 
 * @author Nick.Zhang
 *
 */
@RestController
@RequestMapping("/changeForm")
public class ChangeFormController {
	
	@Autowired
	private ChangeFormService changeFormService;
	
	@RequestMapping(value = "/findChangeFormByPage", method = RequestMethod.GET)
	public Object findEquiomentRecord(HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		Long u_id = null;
		if(request.getParameter("user")!=null&&!"".equals(request.getParameter("user"))) {
			u_id = Long.parseLong(request.getParameter("user"));
		}
		String status = request.getParameter("status");
		String change_time = request.getParameter("change_time");
		if(change_time!=null&&!"".equals(change_time)) {
			String startTime = change_time.substring(0, 10);
			String endTime = change_time.substring(13,change_time.length());
			if(!"".equals(endTime)&&endTime!=null&&!"".equals(startTime)&&startTime!=null){
	            startTime = startTime+" 00:00:00";
				endTime = endTime+" 23:59:59";
	        }
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
		}
		int temp = limit;
		if (page != 1) {
			page = (page - 1) * temp;
		} else {
			page = 0;
		}
		ChangeFormExt changeFormExt= new ChangeFormExt();
		paramMap.put("status", status);
		paramMap.put("page", page);
		paramMap.put("limit", limit);
		paramMap.put("u_id", u_id);
		HashMap<String, Object> result = (HashMap<String, Object>) changeFormService.findAllChangeFormByPage(paramMap,
				changeFormExt);
		result.put("msg", "");
		return result;
	}
	@RequestMapping(value = "/updateChangeFormById", method = RequestMethod.GET)
	public Object updateChangeFormById(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		SystemResult result = changeFormService.updateChangeFormById(id);
		return result;
	}
	
}
