package com.bionime.pojo;

public class Province {
	
	private static final long serialVersionUID = 1L;
	
	private String name;//省份名称
	
	private Integer value;//省份医院数量

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}
