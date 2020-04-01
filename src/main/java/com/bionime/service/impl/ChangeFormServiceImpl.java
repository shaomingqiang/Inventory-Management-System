package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bionime.mapper.ChangeFormMapper;
import com.bionime.pojo.ChangeForm;
import com.bionime.service.ChangeFormService;
import com.bionime.utils.SystemResult;
/**
 * 
 * @author Nick.Zhang
 *
 */
public class ChangeFormServiceImpl implements ChangeFormService {
	
	@Autowired
	ChangeFormMapper changeFormMapper;

	@Override
	public SystemResult insert(ChangeForm changeForm) {
		changeFormMapper.insert(changeForm);
		return SystemResult.ok();

	}

}
