package com.bionime.service;


import java.util.HashMap;
import java.util.Map;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentRecord;
import com.bionime.pojo.EquipmentType;
import com.bionime.utils.SystemResult;

public interface EquipmentRecordService {
	SystemResult insert(EquipmentRecord equipmentRecord);
}
