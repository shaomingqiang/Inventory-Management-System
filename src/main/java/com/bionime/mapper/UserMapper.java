package com.bionime.mapper;

import java.util.List;

import com.bionime.pojo.User;

public interface UserMapper {
	int insert(User user);
	User login(User user);
	List<User> findAllUsers();
}
