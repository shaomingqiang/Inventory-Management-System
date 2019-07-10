package com.bionime.mapper;

import java.util.List;

import com.bionime.pojo.User;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午11:10:49
 * 
 */
public interface UserMapper {
	List<User> findAll();
}
