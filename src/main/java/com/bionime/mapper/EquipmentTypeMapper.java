package com.bionime.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bionime.pojo.EquipmentType;

public interface EquipmentTypeMapper {
	int insert(EquipmentType equipmentType);
	List<String> selectDistinctEquipmentType();
	List<EquipmentType> selectByName(EquipmentType equipmentType);
	int countIncrease(Long id,int size);
}
