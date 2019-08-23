package com.bionime.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;

public interface EquipmentMapper {
	int insert(List<Equipment> equipmentList);
	List<Equipment> selectByType(EquipmentType equipmentType);
	List<Equipment> selectBySn(List<String> sns);
	int statusChange(@Param("ids")List<String> ids,@Param("status")int status,@Param("h_id")Long h_id,@Param("d_id")Long d_id);
	int updateEquimentExtById(@Param("id")int id);
	int updateEquimentExt(EquipmentExt equipmentExt);
	int selectCountByStatus(@Param("type")String type,@Param("status")int status,@Param("name")String name);
	List<EquipmentExt> selectEquimentExt(EquipmentExt equipmentExt);
	List<EquipmentExt> selectEquimentExtByPage(HashMap<String, Object> map);
}
