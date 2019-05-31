/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车辆信息Entity
 * @author zr
 * @version 2018-08-03
 */
public class OcrCarInfo extends DataEntity<OcrCarInfo> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String carBand;		// 车辆品牌
	private String plateNum;		// 车牌号
	private String terminalPhone;		// 车载设备手机号
	private int isDelete;		// 是否删除
	private Date createDate;		// 创建日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarBand() {
		return carBand;
	}
	public void setCarBand(String carBand) {
		this.carBand = carBand;
	}
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public String getTerminalPhone() {
		return terminalPhone;
	}
	public void setTerminalPhone(String terminalPhone) {
		this.terminalPhone = terminalPhone;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}