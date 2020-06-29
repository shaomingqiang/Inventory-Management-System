package com.bionime.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;
import com.bionime.utils.SystemResult;

public interface EquipmentService {
	SystemResult insert(Equipment equipment,Long uid);
	SystemResult selectByType(EquipmentType equipmentType);
	SystemResult statusChange(String ids,int status,Long h_id,Long d_id,Long uid,String description);
	SystemResult updateEquimentExtById(int id);
	SystemResult updateEquimentExt(EquipmentExt equipmentExt,Long uid);
	Map<String, Object> selectCountByStatus();
	Map<String, String> selectHospitalDetail(Long id);
	SystemResult selectEquimentExt(EquipmentExt equipmentExt);
	Map<String, Object> selectEquimentExtByPage(HashMap<String, Object> map,EquipmentExt equipmentExt);
	Equipment selectEquipmentIdBySn(String sn);
	List<Equipment> selectEquipmentIdByDid(Long d_id);
	List<Equipment> selectBySn(List<String> Sns);
	List<Equipment> selectById(List<String> ids);
	public void sendSimpleMail(String to, String subject, String content);//发送简单邮件
}
