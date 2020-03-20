package com.bionime.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bionime.mapper.HospitalMapper;
import com.bionime.pojo.Department;
import com.bionime.pojo.EquipmentExt;
import com.bionime.pojo.EquipmentType;
import com.bionime.pojo.Hospital;
import com.bionime.pojo.HospitalExt;
import com.bionime.pojo.Province;
import com.bionime.service.HospitalService;
import com.bionime.utils.JsonUtils;
import com.bionime.utils.SystemResult;


/**
 * 
 * <p>Title: HospitalServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:31:33
 * @version 1.0
 */
@Transactional
@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalMapper hospitalMapper;
	
	@Override
	public SystemResult insert(Hospital hospital) {
		if(hospitalMapper.selectHospitalExt(hospital)!=null && hospitalMapper.selectHospitalExt(hospital).size()>0){
			return SystemResult.build(500, "医院已存在，请重新录入！");
		}
		hospitalMapper.insert(hospital);
		return SystemResult.ok();
	}

	@Override
	public SystemResult selectHospital(Hospital hospital) {
		List<Hospital> result = hospitalMapper.selectHospitalExt(hospital);
		return SystemResult.ok(result);
	}
	
	
	@Override
	public SystemResult selectHospitalByProvince(Hospital hospital) {
		List<Hospital> hospitallist = hospitalMapper.selectHospitalExt(hospital);
		Map<String,Long> typeMap = new HashMap<String,Long>();
		for (Hospital hospital1 : hospitallist) {
			typeMap.put(hospital1.getName(), hospital1.getId());
		}
		SystemResult result = SystemResult.ok(typeMap);
		return result;
	}
	
	
	@Override
	public SystemResult selectDepartmentByHospital(Long id) {
		List<Department> hospitallist = hospitalMapper.selectDepartment(id);
		Map<String,Long> typeMap = new HashMap<String,Long>();
		for (Department hospital1 : hospitallist) {
			typeMap.put(hospital1.getName(), hospital1.getId());
		}
		SystemResult result = SystemResult.ok(typeMap);
		return result;
	}
	

	@Override
	public Map<String, Object> selectHospitalExtByPage(HashMap<String, Object> hospitalMap) {
		List<HospitalExt> hospitalExtListByPage = hospitalMapper.selectHospitalExtByPage(hospitalMap);
		//查询每个医院的科室数量
		List<HospitalExt> hospitalExtDCount =  hospitalMapper.selectHospitalExtDCount();
		//查询出医院的总数
		List<Hospital> hospitalAllList = hospitalMapper.selectHospitalExt(new Hospital());
		int count = hospitalAllList.size();
		for(int j=0; j < hospitalExtListByPage.size();j++) {
			for(int i=0; i< hospitalExtDCount.size();i++) {
				if(hospitalExtDCount.get(i).getId()==hospitalExtListByPage.get(j).getId()) {
					hospitalExtListByPage.get(j).setDname(hospitalExtDCount.get(i).getDname());
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("data",hospitalExtListByPage);
		map.put("count", count);
		return map;
	}

	@Override
	public SystemResult selectHospitalById(Long id) {
		List<Hospital> result = hospitalMapper.selectHospitalById(id);
		return SystemResult.ok(result);
	}

	@Override
	public SystemResult updateHospital(Hospital hospital) {
		int updateHospital = hospitalMapper.updateHospital(hospital);
		return SystemResult.ok();
	}

	@Override
	public SystemResult getProvinceData() {
		List<Province> provinceData = hospitalMapper.getProvinceData();
		return SystemResult.ok(provinceData);
	}
}
