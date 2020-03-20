package com.bionime.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E-mail:kevin.zhou@bionime.com
 * @version 创建时间：2020年3月20日 下午15:56:51 设备额外记录类
 */
public class EquipmentRecordExt implements Serializable{

	private static final long serialVersionUID = -7794217535054018978L;	
	private Long id;	
	private Long e_id;//设备id，外键
	private String sn;
	private String change_type;
	private Date change_time;
	private Long operator;
	private Long oname;
	private Long h_id;
	private Long d_id;
	private Long hname;
	private Long dname;
	private String remarks;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getE_id() {
		return e_id;
	}
	public void setE_id(Long e_id) {
		this.e_id = e_id;
	}
	public String getChange_type() {
		return change_type;
	}
	public void setChange_type(String change_type) {
		this.change_type = change_type;
	}
	public Date getChange_time() {
		return change_time;
	}
	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}
	public Long getOperator() {
		return operator;
	}
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	
	public Long getHname() {
		return hname;
	}
	public void setHname(Long hname) {
		this.hname = hname;
	}
	public Long getDname() {
		return dname;
	}
	public void setDname(Long dname) {
		this.dname = dname;
	}
	public Long getH_id() {
		return h_id;
	}
	public void setH_id(Long h_id) {
		this.h_id = h_id;
	}
	public Long getD_id() {
		return d_id;
	}
	public void setD_id(Long d_id) {
		this.d_id = d_id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}