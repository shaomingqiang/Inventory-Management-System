package com.bionime.mapper;

import java.util.HashMap;
import java.util.List;

import com.bionime.pojo.ChangeForm;
import com.bionime.pojo.ChangeFormExt;
import com.bionime.pojo.Hospital;

public interface ChangeFormMapper {
	int insert(ChangeForm changeForm);
	List<ChangeFormExt> findAllChangeFormByPage(HashMap<String, Object> map);
	List<ChangeFormExt> findAllChangeForm(HashMap<String, Object> map);
	//根据id修改delete_tag为1
	int updateChangeFormById(Long id);
}
