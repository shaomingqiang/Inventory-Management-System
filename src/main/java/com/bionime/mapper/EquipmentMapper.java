package com.bionime.mapper;

import java.util.List;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentType;

public interface EquipmentMapper {
	int insert(Equipment equipment);
	List<Equipment> selectByType(EquipmentType equipmentType);
}
