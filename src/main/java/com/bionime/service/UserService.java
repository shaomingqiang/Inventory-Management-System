package com.bionime.service;

import com.bionime.pojo.User;
import com.bionime.utils.SystemResult;

public interface UserService {
	void insert(User user);
	SystemResult login(User user);
}
