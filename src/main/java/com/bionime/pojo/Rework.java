package com.bionime.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午10:31:04 返修关系类
 */
public class Rework implements Serializable {
	private static final long serialVersionUID = -4476804318786270200L;

	private Long id;

	private Long me_id;// 返修设备id，外键

	private Date me_time;// 设备返修时间

	private Long fe_id;// 场内归还设备id,外键

	private Date fe_time;// 场内归还设备时间

	private String remarks;// 备注信息

	private Long u_id;// 操作人员id，外键

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMe_id() {
		return me_id;
	}

	public void setMe_id(Long me_id) {
		this.me_id = me_id;
	}

	public Date getMe_time() {
		return me_time;
	}

	public void setMe_time(Date me_time) {
		this.me_time = me_time;
	}

	public Long getFe_id() {
		return fe_id;
	}

	public void setFe_id(Long fe_id) {
		this.fe_id = fe_id;
	}

	public Date getFe_time() {
		return fe_time;
	}

	public void setFe_time(Date fe_time) {
		this.fe_time = fe_time;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getU_id() {
		return u_id;
	}

	public void setU_id(Long u_id) {
		this.u_id = u_id;
	}
}
