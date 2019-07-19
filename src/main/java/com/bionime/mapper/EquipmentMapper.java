package com.bionime.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;

public interface EquipmentMapper {
	int insert(List<Equipment> equipmentList);
	List<Equipment> selectByType(EquipmentType equipmentType);
	List<Equipment> selectBySn(List<String> sns);
	int statusChange(@Param("ids")List<String> ids,@Param("status")int status);
	List<EquipmentExt> selectEquimentExt();
	List<EquipmentExt> selectEquimentExtByPage(@Param("page")int page,@Param("pageSize")int pageSize);
}
