package com.bionime.controller;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
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
 * <p>Title: EquipmentController</p>
 * <p>Description:</p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:25:34
 * @version 1.0
 */
@RestController
@RequestMapping("/equipment")
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;

	/**
	 * 设备添加
	 * <p>Title: insertEquipment</p>
	 * <p>Description: </p>
	 * @param equipment
	 * @return
	 */
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public SystemResult insertEquipment(@RequestBody Equipment equipment) {
		SystemResult result = equipmentService.insert(equipment);
		return result;
	}
	
	/**
	 * 根据设备类型选择设备
	 * <p>Title: insertEquipment</p>
	 * <p>Description: </p>
	 * @param equipmentType
	 * @return
	 */
	@RequestMapping(value="/selectByType",method = RequestMethod.POST)
	public SystemResult insertEquipment(@RequestBody EquipmentType equipmentType) {
		SystemResult result = equipmentService.selectByType(equipmentType);
		return result;
	}
	
	/**
	 * 批量修改设备状态
	 * <p>Title: equipmentStatusChange</p>
	 * <p>Description: </p>
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/statusChange",method = RequestMethod.POST)
	public SystemResult equipmentStatusChange(String id, Integer status){
		SystemResult result = equipmentService.statusChange(id, status);
		return result;
	}
	
	@RequestMapping(value="/selectEquimentExt",method = RequestMethod.POST)
	public SystemResult selectEquimentExt(@RequestBody EquipmentExt equipmentExt){
		if(equipmentExt.getSn()!=null){
			equipmentExt.setSn("%"+equipmentExt.getSn()+"%");
		}
		SystemResult result = equipmentService.selectEquimentExt(equipmentExt);
		return result;
	}
}
