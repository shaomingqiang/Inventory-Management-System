package com.bionime.pojo;

import java.io.Serializable;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午10:50:01 设备类型类
 */
public class EquipmentType implements Serializable{
	
	private static final long serialVersionUID = -8071447928213344294L;
	
	private Long id;
	
	private String name;//设备名称
	
	private String count;//设备数量
	
	private String explian;//设备描述
	
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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getExplian() {
		return explian;
	}

	public void setExplian(String explian) {
		this.explian = explian;
	}

	public Boolean getDelete_tag() {
		return delete_tag;
	}

	public void setDelete_tag(Boolean delete_tag) {
		this.delete_tag = delete_tag;
	}
	
	
}
