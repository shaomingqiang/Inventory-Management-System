package com.bionime.service;

import com.bionime.pojo.EquipmentType;
import com.bionime.utils.SystemResult;

public interface EquipmentTypeService {
	SystemResult insert(EquipmentType equipmentType);
	SystemResult selectDistinctEquipmentType();
	SystemResult selectByName(EquipmentType equipmentType);
}
