package com.bionime.pojo;

import java.io.Serializable;

public class UserEmail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long value;
	private String title;
	private String email;
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
