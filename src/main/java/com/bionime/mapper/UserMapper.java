package com.bionime.mapper;

import com.bionime.pojo.User;

public interface UserMapper {
	int insert(User user);
	User login(User user);
}
