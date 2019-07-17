package com.bionime.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentType;

public interface EquipmentMapper {
	int insert(List<Equipment> equipmentList);
	List<Equipment> selectByType(EquipmentType equipmentType);
	List<Equipment> selectBySn(List<String> sns);
	int equipmentStateChange(@Param("id")Long id,@Param("status")int status);
}
