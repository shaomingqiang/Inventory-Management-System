package com.bionime.pojo;

import java.io.Serializable;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午11:01:28 科室类
 */
public class Department implements Serializable{

	private static final long serialVersionUID = 5372050406181861479L;
	
	private Long id;
	
	private String name;//科室名称
	
	private Long h_id;//所在医院id，外键
	
	private String remarks;//备注信息
	
	private Boolean delete_tag;//0是在用，1是不用

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getH_id() {
		return h_id;
	}

	public void setH_id(Long h_id) {
		this.h_id = h_id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getDelete_tag() {
		return delete_tag;
	}

	public void setDelete_tag(Boolean delete_tag) {
		this.delete_tag = delete_tag;
	}
	
}
