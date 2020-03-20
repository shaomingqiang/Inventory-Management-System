package com.bionime.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.EquipmentRecord;
import com.bionime.pojo.User;
import com.bionime.service.EquipmentRecordService;
import com.bionime.service.UserService;
import com.bionime.utils.SystemResult;

/**
 * 
 * <p>Title: EquipmentRecordController</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年9月24日下午2:14:15
 * @version 1.0
 */
@RestController
@RequestMapping("/equipmentrecord")
public class EquipmentRecordController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EquipmentRecordService equipmentRecordService;

	/**
	 * 设备记录添加
	 * <p>Title: insert</p>
	 * <p>Description: </p>
	 * @param user
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public SystemResult insert(@RequestBody EquipmentRecord equipmentRecord) {
		SystemResult result = equipmentRecordService.insert(equipmentRecord);
		return result;
	}
	
	@RequestMapping(value = "/findEquiomentRecord", method = RequestMethod.POST)
	public Object findEquiomentRecord(HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		Long e_id = Long.parseLong(request.getParameter("e_id"));
		int temp = limit;
		if (page != 1) {
			page = (page - 1) * temp;
		} else {
			page = 0;
		}
		EquipmentRecord equipmentRecord= new EquipmentRecord();
		equipmentRecord.setE_id(e_id);
		paramMap.put("page", page);
		paramMap.put("limit", limit);
		paramMap.put("e_id", e_id);
		HashMap<String, Object> result = (HashMap<String, Object>) equipmentRecordService.findEquiomentRecordByPage(paramMap,
				equipmentRecord);
		result.put("msg", "");
		return result;
	}

}
