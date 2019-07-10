package com.bionime.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午10:54:51 设备记录类
 */
public class EquipmentRecord implements Serializable{

	private static final long serialVersionUID = -7794217535054018978L;
	
	private Long id;
	
	private Long e_id;//设备id，外键
	
	private Date in_hospital;//入院时间
	
	private Date be_time;//借用时间
	
	private Date me_time;//返修时间
	
	private Date fe_time;//场内归还设备时间

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

	public Date getIn_hospital() {
		return in_hospital;
	}

	public void setIn_hospital(Date in_hospital) {
		this.in_hospital = in_hospital;
	}

	public Date getBe_time() {
		return be_time;
	}

	public void setBe_time(Date be_time) {
		this.be_time = be_time;
	}

	public Date getMe_time() {
		return me_time;
	}

	public void setMe_time(Date me_time) {
		this.me_time = me_time;
	}

	public Date getFe_time() {
		return fe_time;
	}

	public void setFe_time(Date fe_time) {
		this.fe_time = fe_time;
	}
	
	
}
