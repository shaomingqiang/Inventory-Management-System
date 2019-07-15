package com.bionime.mapper;

import java.util.List;

import com.bionime.pojo.EquipmentType;

public interface EquipmentTypeMapper {
	int insert(EquipmentType equipmentType);
	List<String> selectDistinctEquipmentType();
}
