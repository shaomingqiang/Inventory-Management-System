package com.bionime.pojo;

import java.io.Serializable;

/**
* @author 作者 E-mail:nick.zhang@bionime.com
* @version 创建时间：2019年8月29日 下午3:04:25
* 科室包含仪器明细信息
*/
public class DepartmentDetail implements Serializable{
	
	private static final long serialVersionUID = -8038869222401350005L;

	private Long meterId;//meter的id
	
	private Long h_id;//所在医院的id
	
	private Long d_id;//所在科室的id
	
	private Long e_did;//血糖仪所在科室的id

	private String sn;//序列号
	
	private String status;//meter状态
	
	private String dName;//科室名称
	
	private String inDepartmentTime;//入科时间

	public Long getD_id() {
		return d_id;
	}

	public void setD_id(Long d_id) {
		this.d_id = d_id;
	}

	public Long getH_id() {
		return h_id;
	}

	public void setH_id(Long h_id) {
		this.h_id = h_id;
	}

	public Long getE_did() {
		return e_did;
	}

	public void setE_did(Long e_did) {
		this.e_did = e_did;
	}

	public Long getMeterId() {
		return meterId;
	}

	public void setMeterId(Long meterId) {
		this.meterId = meterId;
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

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getInDepartmentTime() {
		return inDepartmentTime;
	}

	public void setInDepartmentTime(String inDepartmentTime) {
		this.inDepartmentTime = inDepartmentTime;
	}
	
}
