package com.bionime.service;


import java.util.HashMap;
import java.util.Map;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;
import com.bionime.utils.SystemResult;

public interface EquipmentService {
	SystemResult insert(Equipment equipment);
	SystemResult selectByType(EquipmentType equipmentType);
	SystemResult statusChange(String ids,int status,Long h_id,Long d_id);
	SystemResult updateEquimentExtById(int id);
	SystemResult updateEquimentExt(EquipmentExt equipmentExt);
	Map<String, Object> selectCountByStatus();
	SystemResult selectEquimentExt(EquipmentExt equipmentExt);
	Map<String, Object> selectEquimentExtByPage(HashMap<String, Object> map,EquipmentExt equipmentExt);
	Equipment selectEquipmentIdBySn(String sn);
}
