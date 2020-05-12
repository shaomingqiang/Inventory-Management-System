package com.bionime.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.ChangeFormMapper;
import com.bionime.pojo.ChangeForm;
import com.bionime.pojo.ChangeFormExt;
import com.bionime.pojo.EquipmentRecordExt;
import com.bionime.service.ChangeFormService;
import com.bionime.utils.SystemResult;
/**
 * 
 * @author Nick.Zhang
 *
 */
@Transactional
@Service
public class ChangeFormServiceImpl implements ChangeFormService {
	
	@Autowired
	ChangeFormMapper changeFormMapper;

	@Override
	public SystemResult insert(ChangeForm changeForm) {
		changeFormMapper.insert(changeForm);
		return SystemResult.ok();

	}

	@Override
	public Map<String, Object> findAllChangeFormByPage(HashMap<String, Object> paramMap, ChangeFormExt changeFormExt) {
		List<ChangeFormExt> list = changeFormMapper.findAllChangeFormByPage(paramMap);
		for (ChangeFormExt changeFormExt2 : list) {
			changeFormExt2.setF_date(changeFormExt2.getF_date().substring(0, 16));
			if("20".equals(changeFormExt2.getType())) {
				changeFormExt2.setType("入院");
			}else if("30".equals(changeFormExt2.getType())) {
				changeFormExt2.setType("出库");
			}else if("40".equals(changeFormExt2.getType())) {
				changeFormExt2.setType("借用");
			}else if("70".equals(changeFormExt2.getType())) {
				changeFormExt2.setType("故障");
			}
		}
		HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("startTime", paramMap.get("startTime"));
		paramMap2.put("endTime", paramMap.get("endTime"));
		paramMap2.put("status", paramMap.get("status"));
		paramMap2.put("u_id", paramMap.get("u_id"));
		List<ChangeFormExt> findAllChangeForm = changeFormMapper.findAllChangeForm(paramMap2);
		int count = findAllChangeForm.size();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("data",list);
		map.put("count", count);
		return map;
	}

	@Override
	public SystemResult updateChangeFormById(Long id) {
		int updateChangeFormById = changeFormMapper.updateChangeFormById(id);
		return SystemResult.ok();
	}

}
