/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单管理Entity
 * @author zr
 * @version 2017-08-07
 */
public class OcrOrder extends DataEntity<OcrOrder> {
	
	private static final long serialVersionUID = 1L;
	private String companyId;		// 公司ID
	private String vehicleType;		// 车辆类型
	private String paymentMode;		// 支付方式
	private String cost;		// 费用
	private String userId;		// 用户ID
	private String peopleNumber;		// 同行人数
	private String appearPurpose;		// 出现目的
	private String status;		// 状态
	private String driverId;		// 司机ID
	private String departurePlace;		// 出发地
	private String destination;		// 目的地
	private Date departureDate;		// 出发时间
	private Date planArrivalDate;		// 预计到达时间
	private String reason;		// 拒绝理由
	private Date beginDepartureDate;		// 开始 出发时间
	private Date endDepartureDate;		// 结束 出发时间
	
	public OcrOrder() {
		super();
	}

	public OcrOrder(String id){
		super(id);
	}

	@Length(min=0, max=64, message="公司ID长度必须介于 0 和 64 之间")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Length(min=0, max=100, message="车辆类型长度必须介于 0 和 100 之间")
	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Length(min=1, max=1, message="支付方式长度必须介于 1 和 1 之间")
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Length(min=0, max=64, message="用户ID长度必须介于 0 和 64 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=11, message="同行人数长度必须介于 1 和 11 之间")
	public String getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	
	@Length(min=0, max=100, message="出现目的长度必须介于 0 和 100 之间")
	public String getAppearPurpose() {
		return appearPurpose;
	}

	public void setAppearPurpose(String appearPurpose) {
		this.appearPurpose = appearPurpose;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="司机ID长度必须介于 0 和 64 之间")
	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	@Length(min=1, max=100, message="出发地长度必须介于 1 和 100 之间")
	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}
	
	@Length(min=1, max=100, message="目的地长度必须介于 1 和 100 之间")
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanArrivalDate() {
		return planArrivalDate;
	}

	public void setPlanArrivalDate(Date planArrivalDate) {
		this.planArrivalDate = planArrivalDate;
	}
	
	@Length(min=0, max=100, message="拒绝理由长度必须介于 0 和 100 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Date getBeginDepartureDate() {
		return beginDepartureDate;
	}

	public void setBeginDepartureDate(Date beginDepartureDate) {
		this.beginDepartureDate = beginDepartureDate;
	}
	
	public Date getEndDepartureDate() {
		return endDepartureDate;
	}

	public void setEndDepartureDate(Date endDepartureDate) {
		this.endDepartureDate = endDepartureDate;
	}
		
}