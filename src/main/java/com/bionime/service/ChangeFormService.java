package com.bionime.service;

import java.util.HashMap;
import java.util.Map;

import com.bionime.pojo.ChangeForm;
import com.bionime.pojo.ChangeFormExt;
import com.bionime.utils.SystemResult;

public interface ChangeFormService {
	SystemResult insert(ChangeForm  changeForm);
	Map<String, Object> findAllChangeFormByPage(HashMap<String, Object> map,ChangeFormExt changeFormExt);
	SystemResult updateChangeFormById(Long id);
}
