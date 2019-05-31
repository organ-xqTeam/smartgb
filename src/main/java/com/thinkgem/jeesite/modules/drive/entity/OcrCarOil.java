/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车辆油耗Entity
 * @author ybc
 * @version 2018-08-03
 */
public class OcrCarOil extends DataEntity<OcrCarOil> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String carId;		// OcrCarInfo主键ID
	private Date driverDate;		// 行驶日期
	private String oilNum;		// 油耗总数
	private Date createDate;		// 创建日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public Date getDriverDate() {
		return driverDate;
	}
	public void setDriverDate(Date driverDate) {
		this.driverDate = driverDate;
	}
	public String getOilNum() {
		return oilNum;
	}
	public void setOilNum(String oilNum) {
		this.oilNum = oilNum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}