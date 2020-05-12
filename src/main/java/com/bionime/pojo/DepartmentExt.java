package com.bionime.pojo;

public class DepartmentExt extends Department {
	
	private static final long serialVersionUID = -1418664779018295713L;
	private String province;
	private String level;
	private Hospital hospital;
	
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
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
	
	
	
	
	
	
}
