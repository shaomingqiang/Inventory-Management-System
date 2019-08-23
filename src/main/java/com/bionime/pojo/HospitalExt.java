package com.bionime.pojo;


/**
 * @author 作者 Ben.Guo@bionime.com
 * @version 创建时间：2019年8月22日 医院类扩展
 */
public class HospitalExt extends Hospital{

	private String dname;// 科室名称

	private String meter;// Meter使用数量

	private String meterTotal;// Meter总数量
	
	private String tname;// 设备类型

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getMeter() {
		return meter;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}

	public String getMeterTotal() {
		return meterTotal;
	}

	public void setMeterTotal(String meterTotal) {
		this.meterTotal = meterTotal;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
	

}
