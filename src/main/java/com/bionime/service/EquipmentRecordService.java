package com.bionime.service;


import java.util.HashMap;
import java.util.Map;

import com.bionime.pojo.EquipmentRecord;
import com.bionime.utils.SystemResult;

public interface EquipmentRecordService {
	SystemResult insert(EquipmentRecord equipmentRecord);
	Map<String, Object> findEquiomentRecordByPage(HashMap<String, Object> map,EquipmentRecord equipmentRecord);
}
