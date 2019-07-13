package com.bionime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bionime.pojo.Borrow;
import com.bionime.service.BorrowService;



/**
 * 
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Company: www.bionime.com</p> 
 * @author	Ethan.Shao
 * @date	2019年7月11日下午3:30:55
 * @version 1.0
 */
@RestController
@RequestMapping("/borrow")
public class BorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public void insert(@RequestBody Borrow borrow) {
		borrowService.insert(borrow);
	}
}
