/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriver;
import com.thinkgem.jeesite.modules.ocruser.entity.OcrUser;

/**
 * 订单管理Entity
 * @author zr
 * @version 2017-08-07
 */
public class OcrOrderVO extends DataEntity<OcrOrderVO> {
	//声明静态status量：
	//0：待指派
	public static String STATUS_TO_ASSIGNED="0";
	//1：待确认
	public static String STATUS_TO_ASSURE="1";
	//2：已确认
	public static String STATUS_BE_CONFIRMED="2";
	//3：被拒绝
	public static String STATUS_BE_REFUSED="3";
	//4：行程中
	public static String STATUS_IN_TRIP="4";
	//5：已送达
	public static String STATUS_BE_ARRIVED="5";
	//6：已取消
	public static String STATUS_BE_CANCELED="6";
	//7：已还车
	public static String STATUS_BE_RETURN_CAR="7";
	private static final long serialVersionUID = 1L;
	private String companyId;		// 公司ID
	private String vehicleType;		// 车辆类型
	private String paymentMode;		// 支付方式
	private String cost;		// 费用
	private OcrUser user;		// 用户ID
	private String phone;
	private String peopleNumber;		// 同行人数
	private String appearPurpose;		// 出现目的
	private String status;		// 状态
	private OcrDriver driver;		// 司机ID
	private OcrVehicle vehicle;	//车辆
	private String departurePlace;		// 出发地
	private String destination;		// 目的地
	private Date departureDate;		// 出发时间
	private Date planArrivalDate;
	private Date realDepartureDate;		// 实际出发时间
	private Date realArrivalDate;// 实际到达时间
	private String reason;		// 拒绝理由
	private Date beginDepartureDate;		// 开始 出发时间
	private Date endDepartureDate;		// 结束 出发时间
	private String taskStatus;
	private Double orderOil;
	public OcrOrderVO() {
		super();
		status = "all";
	}

	public OcrOrderVO(String id){
		super(id);
	}

	
	@Length(min=0, max=64, message="公司ID长度必须介于 0 和 64 之间")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public OcrUser getUser() {
		return user;
	}

	public void setUser(OcrUser user) {
		this.user = user;
	}

	public OcrDriver getDriver() {
		return driver;
	}

	public void setDriver(OcrDriver driver) {
		this.driver = driver;
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

	public OcrVehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(OcrVehicle vehicle) {
		this.vehicle = vehicle;
	}
	@Length(min=0, max=100, message="电话长度必须介于 0 和 100 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getRealDepartureDate() {
		return realDepartureDate;
	}

	public void setRealDepartureDate(Date realDepartureDate) {
		this.realDepartureDate = realDepartureDate;
	}

	public Date getRealArrivalDate() {
		return realArrivalDate;
	}

	public void setRealArrivalDate(Date realArrivalDate) {
		this.realArrivalDate = realArrivalDate;
	}

	public Double getOrderOil() {
		return orderOil;
	}

	public void setOrderOil(Double orderOil) {
		this.orderOil = orderOil;
	}


	
}