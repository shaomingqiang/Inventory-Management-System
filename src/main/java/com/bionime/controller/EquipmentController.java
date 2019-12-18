package com.bionime.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bionime.mapper.UserMapper;
import com.bionime.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;
import com.bionime.service.EquipmentService;
import com.bionime.utils.SystemResult;

/**
 * 
 * <p>
 * Title: EquipmentController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.bionime.com
 * </p>
 * 
 * @author Ethan.Shao
 * @date 2019年7月11日下午2:25:34
 * @version 1.0
 */
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 设备添加
	 * <p>
	 * Title: insertEquipment
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param equipment
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public SystemResult insertEquipment(HttpServletRequest request,@RequestBody Equipment equipment) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		user=userMapper.login(user);
		SystemResult result = equipmentService.insert(equipment,user.getId());
		return result;
	}

	/**
	 * 根据设备类型选择设备
	 * <p>
	 * Title: insertEquipment
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param equipmentType
	 * @return
	 */
	@RequestMapping(value = "/selectByType", method = RequestMethod.POST)
	public SystemResult insertEquipment(@RequestBody EquipmentType equipmentType) {
		SystemResult result = equipmentService.selectByType(equipmentType);
		return result;
	}

	/**
	 * 批量修改设备状态
	 * <p>
	 * Title: equipmentStatusChange
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/statusChange", method = RequestMethod.POST)
	public SystemResult equipmentStatusChange(HttpServletRequest request,String id, Integer status,Long h_id,Long d_id) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		user=userMapper.login(user);
		SystemResult result = equipmentService.statusChange(id, status,h_id,d_id,user.getId());
		return result;
	}
	
	@RequestMapping(value = "/selectCountByStatus", method = RequestMethod.POST)
	public Object selectCountByStatus() {
		HashMap<String, Object> result = (HashMap<String, Object>) equipmentService.selectCountByStatus();
		return result;
	}
	
	@RequestMapping(value = "/updateEquimentById", method = RequestMethod.POST)
	public SystemResult updateEquimentById(Integer id) {
		SystemResult result = equipmentService.updateEquimentExtById(id);
		return result;
	}
	
	@RequestMapping(value = "/updateEquiment", method = RequestMethod.POST)
	public SystemResult updateEquiment(@RequestBody EquipmentExt equipmentExt) {
		SystemResult result = equipmentService.updateEquimentExt(equipmentExt);
		return result;
	}
	
	@RequestMapping(value = "/selectEquimentExt", method = RequestMethod.POST)
	public SystemResult selectEquimentExt(@RequestBody EquipmentExt equipmentExt) {
		if (equipmentExt.getSn() != null) {
			equipmentExt.setSn("%" + equipmentExt.getSn() + "%");
		}
		SystemResult result = equipmentService.selectEquimentExt(equipmentExt);
		return result;
	}
	
	@RequestMapping(value = "/selectHospitalDetail", method = RequestMethod.POST)
	public Object selectHospitalDetail(@RequestBody Equipment equipment) {
		Long Lid = equipment.getId();
		Map<String, String> map = equipmentService.selectHospitalDetail(Lid);
		return map;
	}
	//分页查询加模糊查询
	@RequestMapping(value = "/selectEquimentExtByPage", method = RequestMethod.GET)
	public Object selectEquimentExtByPage(HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String sn = request.getParameter("sn");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		int temp = limit;
		if (page != 1) {
			page = (page - 1) * temp;
		} else {
			page = 0;
		}
		EquipmentExt equipmentExt = new EquipmentExt();
		equipmentExt.setName(name);
		equipmentExt.setSn(sn);
		equipmentExt.setType(type);
		equipmentExt.setStatus(status);
		paramMap.put("page", page);
		paramMap.put("limit", limit);
		paramMap.put("sn", sn);
		paramMap.put("name", name);
		paramMap.put("type", type);
		paramMap.put("status", status);
		HashMap<String, Object> result = (HashMap<String, Object>) equipmentService.selectEquimentExtByPage(paramMap,
				equipmentExt);
		result.put("msg", "");
		return result;
	}

}
