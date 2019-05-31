/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 任务管理Entity
 * orderStatus
 * @author gaoly
 * @version 2017-08-11
 */
public class OcrTaskOrderVO extends DataEntity<OcrTaskOrderVO> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单ID
	private String vehicleId;		// 车辆ID
	private String driverId;		// 司机ID
	private String status;		// 状态【0：待指派，1：待确认，2：已确认，3：被拒绝，4：行程中，5：已送达，6：已取消】
	
	private String departurePlace;		// 出发地
	private String destination;		// 目的地
	private Date departureDate;		// 出发时间
	private Date planArrivalDate;		// 预计到达时间
	private String brandModels;		// 品牌车型
	private String vehicleType;		// 车辆类型
	private String licensePlateNumber;		// 车牌号
	private String seatCount;		// 座位数量
	private String orderStatus;
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

	public OcrTaskOrderVO() {
		super();
	}

	public OcrTaskOrderVO(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=64, message="车辆ID长度必须介于 0 和 64 之间")
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@Length(min=0, max=64, message="司机ID长度必须介于 0 和 64 之间")
	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	@Length(min=0, max=1, message="状态【0：待指派，1：待确认，2：已确认，3：被拒绝，4：行程中，5：已送达，6：已取消】长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}