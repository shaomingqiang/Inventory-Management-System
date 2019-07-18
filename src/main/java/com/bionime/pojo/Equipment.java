package com.bionime.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E-mail:nick.zhang@bionime.com
 * @version 创建时间：2019年7月10日 上午10:28:17 设备类
 */
public class Equipment implements Serializable {
	private static final long serialVersionUID = 1489861846308673946L;
	private Long id;
	private String sn;// 设备序列号
	private String status;// 设备状态 1:在院，2:在库，3:出库，4:借用，5:返修，6:审核
	private Date in_time;// 入库时间
	private String description;// 描述
	private Long h_id;// 所在医院id，外键
	private Long d_id;// 所在科室id，外键
	private Long et_id;// 设备类型id，外键
	private String property_no;// 财产编号
	private Boolean delete_tag;// 0是在用，1是不用

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

	

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getEt_id() {
		return et_id;
	}

	public void setEt_id(Long et_id) {
		this.et_id = et_id;
	}

	public String getProperty_no() {
		return property_no;
	}

	public void setProperty_no(String property_no) {
		this.property_no = property_no;
	}

	public Boolean getDelete_tag() {
		return delete_tag;
	}

	public void setDelete_tag(Boolean delete_tag) {
		this.delete_tag = delete_tag;
	}

}
