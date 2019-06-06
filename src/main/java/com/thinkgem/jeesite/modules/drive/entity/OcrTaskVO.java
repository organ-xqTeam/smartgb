/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 任务值对象
 * @author gaoly
 * @version 2017-08-10
 */
public class OcrTaskVO extends DataEntity<OcrTaskVO> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// 主键
	private String departurePlace;		// 出发地
	private String destination;		// 目的地
	private Date departureDate;		// 出发时间
	private Date planArrivalDate;		// 预计到达时间
	private String brandModels;		// 品牌车型
	private String vehicleType;		// 车辆类型
	private String licensePlateNumber;		// 车牌号
	private String seatCount;		// 座位数量
	private String status;		// 状态
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OcrTaskVO() {
		super();
	}

	public OcrTaskVO(String id){
		super(id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getPlanArrivalDate() {
		return planArrivalDate;
	}

	public void setPlanArrivalDate(Date planArrivalDate) {
		this.planArrivalDate = planArrivalDate;
	}

	public String getBrandModels() {
		return brandModels;
	}

	public void setBrandModels(String brandModels) {
		this.brandModels = brandModels;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}

}