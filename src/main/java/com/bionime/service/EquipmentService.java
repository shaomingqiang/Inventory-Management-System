package com.bionime.service;

import java.util.List;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentType;
import com.bionime.utils.SystemResult;

public interface EquipmentService {
	SystemResult insert(Equipment equipment);
	SystemResult selectByType(EquipmentType equipmentType);
	SystemResult equipmentStatusChange(String ids,int status);
}
