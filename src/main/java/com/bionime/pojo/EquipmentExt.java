package com.bionime.pojo;

public class EquipmentExt{
	private Long id;
	private String sn;// 设备序列号
	private String status;// 设备状态 1:在院，2:在库，3:出库，4:借用，5:返修，6:审核
	private String in_time;// 入库时间
	private String property_no;
	//private EquipmentType equipmentType;
	private String name;
	private String type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public String getIn_time() {
		return in_time;
	}
	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}
	public String getProperty_no() {
		return property_no;
	}
	public void setProperty_no(String property_no) {
		this.property_no = property_no;
	}
	
	
	/*public EquipmentType getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
