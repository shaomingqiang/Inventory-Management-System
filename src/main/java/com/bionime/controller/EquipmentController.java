package com.bionime.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.bionime.mapper.ChangeFormMapper;
import com.bionime.mapper.DepartmentMapper;
import com.bionime.mapper.HospitalMapper;
import com.bionime.mapper.UserMapper;
import com.bionime.pojo.User;
import com.bionime.pojo.UserEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.ChangeForm;
import com.bionime.pojo.Department;
import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;
import com.bionime.pojo.Hospital;
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
	
	@Autowired
	private HospitalMapper hospitalMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private ChangeFormMapper changeFormMapper;
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/statusChange", method = RequestMethod.POST)
	public SystemResult equipmentStatusChange(HttpServletRequest request,String id, Integer status,Long h_id,Long d_id,
			String emailUsersData,String formTag) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		user=userMapper.login(user);
		
		List<UserEmail> usersData = JSONObject.parseArray(emailUsersData,UserEmail.class);
		SystemResult result = equipmentService.statusChange(id, status,h_id,d_id,user.getId());
		sendFormOrEmail(request,id, status,h_id,d_id,user.getId(),formTag,usersData);
		return result;
	}
	
	@SuppressWarnings({ "unused", "null" })
	public void sendFormOrEmail(HttpServletRequest request,String id,int status,Long h_id,Long d_id,Long uid,String formTag,List<UserEmail> usersData) {
		List<String> ids = Arrays.asList(id.split(","));
		
		List<Equipment> equipmentList = equipmentService.selectById(ids);//查询变更机器
		List<Hospital> hospitalList = hospitalMapper.selectHospitalById(h_id);//查询变更医院
		List<Department> departmentList = departmentMapper.selectDepartmentById(d_id);//查询变更科室
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");//操作人员
		String meterSn = null;//变更机器序列号
		for (int i = 0; i < equipmentList.size(); i++) {
			meterSn += equipmentList.get(i).getSn() + ",";
		}
		meterSn = meterSn.substring(4,meterSn.length()-1);
		String hName = null;//医院名称
		if(hospitalList != null && hospitalList.size() == 1) {
			hName = hospitalList.get(0).getName();
		}
		String dName = null;//科室名称
		if(departmentList != null && departmentList.size() == 1) {
			dName = departmentList.get(0).getName();
		}
		List<String> emailList = new ArrayList<String>();//邮箱地址集合
		if(usersData!=null && usersData.size()!=0) {
			for (int i = 0; i < usersData.size(); i++) {
				UserEmail userEmail = usersData.get(i);
				emailList.add(userEmail.getEmail());
				
			}
		}
		String userName = user.getUsername();//操作人员名字
		Long operator = user.getId(); //操作人员id
		if("close".equals(formTag)) {//如果不发单
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = sdf.format(new Date());
		ChangeForm changeForm = new ChangeForm();
		if(status==20) {//在院
			String f_text = null;
			if(dName!=null) {
				f_text = userName+"在"+dateString+"把以下机器："+meterSn+"，放到"+hName+"("+dName+")";
			}else {
				f_text = userName+"在"+dateString+"把以下机器："+meterSn+"，放到"+hName;
			}
			changeForm.setF_text(f_text);
			changeForm.setF_date(new Date());
			changeForm.setOperator(operator);
			changeForm.setType("20");
			changeFormMapper.insert(changeForm);
			if(emailList!=null) {
				for (int i = 0; i < emailList.size(); i++) {
					//发送邮件
					equipmentService.sendSimpleMail(emailList.get(i),"机器入院",f_text);
				}
			}
		}else if(status==30) {//出库
			String f_text = userName+"在"+dateString+"把以下机器："+meterSn+"，改为出库";
			changeForm.setF_text(f_text);
			changeForm.setF_date(new Date());
			changeForm.setOperator(operator);
			changeForm.setType("30");
			changeFormMapper.insert(changeForm);
			if(emailList!=null) {
				for (int i = 0; i < emailList.size(); i++) {
					//发送邮件
					equipmentService.sendSimpleMail(emailList.get(i),"机器出库",f_text);
				}
			}
		}else if(status==40) {//借用
			String f_text = null;
			if(dName!=null) {
				f_text = userName+"在"+dateString+"把以下机器："+meterSn+"，借用到"+hName+"("+dName+")";
			}else {
				f_text = userName+"在"+dateString+"把以下机器："+meterSn+"，借用到"+hName;
			}
			changeForm.setF_text(f_text);
			changeForm.setF_date(new Date());
			changeForm.setOperator(operator);
			changeForm.setType("40");
			changeFormMapper.insert(changeForm);
			if(emailList!=null) {
				for (int i = 0; i < emailList.size(); i++) {
					//发送邮件
					equipmentService.sendSimpleMail(emailList.get(i),"机器借用",f_text);
				}
			}
		}else if(status==70) {//故障
			String f_text = userName+"在"+dateString+"发送以下故障机器："+meterSn;
			changeForm.setF_text(f_text);
			changeForm.setF_date(new Date());
			changeForm.setOperator(operator);
			changeForm.setType("70");
			changeFormMapper.insert(changeForm);
			if(emailList!=null) {
				for (int i = 0; i < emailList.size(); i++) {
					//发送邮件
					equipmentService.sendSimpleMail(emailList.get(i),"机器故障",f_text);
				}
			}
		}else {
			
		}
	}
	
	public void addChangeForm(String f_text,Long operator,Date f_date,String type) {
		ChangeForm changeForm = new ChangeForm();
		changeForm.setF_text(f_text);
		changeForm.setOperator(operator);
		changeForm.setF_date(f_date);
		changeForm.setType(type);
		changeFormMapper.insert(changeForm);
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
	public SystemResult updateEquiment(HttpServletRequest request,String id,String name,String type,
			String sn,String status,String h_id,String d_id,String property_no,String emailUsersData,String formTag) {
		EquipmentExt equipmentExt = new EquipmentExt();
		equipmentExt.setId(Long.parseLong(id));
		equipmentExt.setSn(sn);
		equipmentExt.setStatus(status);
		equipmentExt.setType(type);
		if(!"".equals(h_id)&&h_id!=null) {
			equipmentExt.setH_id(Long.parseLong(h_id));
		}
		equipmentExt.setProperty_no(property_no);
		equipmentExt.setName(name);
		if(!"".equals(d_id)&&d_id!=null) {
			equipmentExt.setD_id(Long.parseLong(d_id));
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		user=userMapper.login(user);
		Integer statusInteger = Integer.parseInt(status);
		Long h_idLong = null;
		if(!"".equals(h_id)&&h_id!=null) {
			 h_idLong = Long.parseLong(h_id);
		}
		Long d_idLong = null;
		if(!"".equals(d_id)&&d_id!=null) {
			 d_idLong = Long.parseLong(d_id);
		}
		List<UserEmail> usersData = JSONObject.parseArray(emailUsersData,UserEmail.class);
		SystemResult result = equipmentService.updateEquimentExt(equipmentExt,user.getId());
		sendFormOrEmail(request,id, statusInteger,h_idLong,d_idLong,user.getId(),formTag,usersData);
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
