package com.bionime.pojo;

import java.util.Date;

public class ChangeFormExt {
	private Long id;
	private String f_text;// 表单内容
	private Long operator;//操作者id
	private String username;//操作者姓名
	private String f_date;//发单日期
	private String type;//发单类型
	private Boolean delete_tag;// 0是在用，1是不用
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getF_text() {
		return f_text;
	}
	public void setF_text(String f_text) {
		this.f_text = f_text;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getF_date() {
		return f_date;
	}
	public void setF_date(String f_date) {
		this.f_date = f_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getDelete_tag() {
		return delete_tag;
	}
	public void setDelete_tag(Boolean delete_tag) {
		this.delete_tag = delete_tag;
	}
	public Long getOperator() {
		return operator;
	}
	public void setOperator(Long operator) {
		this.operator = operator;
	}
}
