package com.bionime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.mapper.BorrowMapper;
import com.bionime.mapper.UserMapper;
import com.bionime.pojo.Borrow;
import com.bionime.pojo.Equipment;
import com.bionime.pojo.User;
import com.bionime.service.BorrowService;
import com.bionime.service.UserService;

/**
 * 
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午2:57:14
 * @version 1.0
 */
@Service
public class BorrowServiceImpl implements BorrowService {

	@Autowired
	private BorrowMapper borrowMapper;
	
	@Override
	public void insert(Borrow borrow) {
		borrowMapper.insert(borrow);

	}

}
