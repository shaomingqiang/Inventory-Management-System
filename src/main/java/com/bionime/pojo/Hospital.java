package com.bionime.pojo;

import java.io.Serializable;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午10:58:16 医院类
 */
public class Hospital implements Serializable {

	private static final long serialVersionUID = 1751974335903903938L;

	private Long id;//

	private String name;// 医院名称

	private String province;// 医院所在省份

	private String level;// 医院级别

	private String remarks;// 医院描述

	private Boolean delete_tag;// 0是在用，1是不用

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
