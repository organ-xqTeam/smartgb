/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.journey.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 行程管理Entity
 * @author gaoly
 * @version 2017-08-09
 */
public class OcrJourney extends DataEntity<OcrJourney> {
	
	private static final long serialVersionUID = 1L;
	private String companyId;		// 公司ID
	private String vehicleType;		// 车辆类型
	private String paymentMode;		// 支付方式【0：在线支付，1：统一结算】
	private String cost;		// 费用
	private User user;		// 用户ID
	private String peopleNumber;		// 同行人数
	private String appearPurpose;		// 出现目的
	private String phone;		// 联系电话
	private String status;		// 状态【0：审核中，1：已拒绝，2：已通过，3：待出发，4：行程中，5：待支付，6：已完成，7：已取消】
	private String driverId;		// 司机ID
	private String departurePlace;		// 出发地
	private String destination;		// 目的地
	private Date departureDate;		// 出发时间
	private Date planArrivalDate;		// 预计到达时间
	private String reason;		// 拒绝理由
	private String areaId;         //出发地id
	private String areaName; // 出发地名称
	private String companyName;// 公司名称
	private String userRealName;// 申请人真实姓名
	private String userJobNumber;// 申请人工号
	
	private String taskId;// 发车单ID
	private String taskStatus;// 发车单状态
	
	private String driverRealName;// 司机真实姓名
	private String driverJobNumber;// 司机工号
	private String driverLoginName;//司机登录名
	private String driverPhone;// 司机电话
	private String brandModels;// 品牌车型
	private String licensePlateNumber;// 车牌号

	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getDriverRealName() {
		return driverRealName;
	}

	public void setDriverRealName(String driverRealName) {
		this.driverRealName = driverRealName;
	}

	public String getDriverJobNumber() {
		return driverJobNumber;
	}

	public void setDriverJobNumber(String driverJobNumber) {
		this.driverJobNumber = driverJobNumber;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getBrandModels() {
		return brandModels;
	}

	public void setBrandModels(String brandModels) {
		this.brandModels = brandModels;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	private String taskInterval;// 任务间隔
	
	public String getTaskInterval() {
		return taskInterval;
	}

	public void setTaskInterval(String taskInterval) {
		this.taskInterval = taskInterval;
	}
	
	@Length(min=0, max=100, message="名称")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Length(min=0, max=100, message="真实姓名")
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	@Length(min=0, max=64, message="工号")
	public String getUserJobNumber() {
		return userJobNumber;
	}

	public void setUserJobNumber(String userJobNumber) {
		this.userJobNumber = userJobNumber;
	}

	public OcrJourney() {
		super();
		status = "2";
		taskInterval = "30";
	}

	public OcrJourney(String id){
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
	
	@Length(min=1, max=1, message="支付方式【0：在线支付，1：统一结算】长度必须介于 1 和 1 之间")
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	@Length(min=0, max=11, message="联系电话长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=1, message="状态【0：审核中，1：已拒绝，2：已通过，3：待出发，4：行程中，5：待支付，6：已完成，7：已取消】长度必须介于 0 和 1 之间")
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

	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
   
	
	


	public String getDriverLoginName() {
		return driverLoginName;
	}

	public void setDriverLoginName(String driverLoginName) {
		this.driverLoginName = driverLoginName;
	}
	
}