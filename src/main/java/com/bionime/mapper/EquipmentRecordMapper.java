package com.bionime.mapper;

import java.util.HashMap;
import java.util.List;

import com.bionime.pojo.EquipmentRecord;

public interface EquipmentRecordMapper {
	int insert(EquipmentRecord equipmentRecord);
	List<EquipmentRecord> findEquiomentRecord(EquipmentRecord equipmentRecord);
	List<EquipmentRecord> findEquiomentRecordByPage(HashMap<String, Object> map);
}
