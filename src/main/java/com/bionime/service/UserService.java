package com.bionime.service;

import java.util.List;

import com.bionime.pojo.User;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午11:14:32
 * 
 */
public interface UserService {
	List<User> findAll();
}
