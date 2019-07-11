package com.bionime.mapper;

import com.bionime.pojo.Equipment;
import com.bionime.pojo.User;

public interface UserMapper {
	void insert(User user);
	User login(User user);
}
