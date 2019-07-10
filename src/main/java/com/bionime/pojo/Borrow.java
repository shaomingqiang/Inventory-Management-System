package com.bionime.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午10:33:16 借用关系类
 */
public class Borrow implements Serializable {

	private static final long serialVersionUID = 3498390547169310668L;

	private Long id;

	private Long be_id;// 借用设备id，外键

	private Date time;// 设备借用时间

	private Long re_id;// 归还设备id,外键

	private Date re_time;// 归还设备时间

	private String remarks;// 备注信息

	private Long u_id;// 操作人员id，外键

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBe_id() {
		return be_id;
	}

	public void setBe_id(Long be_id) {
		this.be_id = be_id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getRe_id() {
		return re_id;
	}

	public void setRe_id(Long re_id) {
		this.re_id = re_id;
	}

	public Date getRe_time() {
		return re_time;
	}

	public void setRe_time(Date re_time) {
		this.re_time = re_time;
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
