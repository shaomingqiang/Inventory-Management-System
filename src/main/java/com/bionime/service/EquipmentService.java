package com.bionime.service;


import java.util.List;
import java.util.Map;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;
import com.bionime.utils.SystemResult;

public interface EquipmentService {
	SystemResult insert(Equipment equipment);
	SystemResult selectByType(EquipmentType equipmentType);
	SystemResult statusChange(String ids,int status);
	SystemResult selectEquimentExt();
	Map<String, Object> selectEquimentExtByPage(int page,int pageSize);
}
