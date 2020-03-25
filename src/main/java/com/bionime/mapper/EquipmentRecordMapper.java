package com.bionime.mapper;

import java.util.HashMap;
import java.util.List;

import com.bionime.pojo.EquipmentRecord;
import com.bionime.pojo.EquipmentRecordExt;

public interface EquipmentRecordMapper {
	int insert(EquipmentRecord equipmentRecord);
	List<EquipmentRecord> findEquiomentRecord(EquipmentRecord equipmentRecord);
	List<EquipmentRecordExt> findEquiomentRecordByPage(HashMap<String, Object> map);
	List<EquipmentRecordExt> findEquiomentRecordByEid(Long e_id);
}
