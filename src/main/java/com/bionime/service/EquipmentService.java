package com.bionime.service;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentType;
import com.bionime.utils.SystemResult;

public interface EquipmentService {
	SystemResult insert(Equipment equipment);
	SystemResult selectByType(EquipmentType equipmentType);
	SystemResult statusChange(String ids,int status);
}
