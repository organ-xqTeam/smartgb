/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;

/**
 * 司机管理Entity
 * @author zr
 * @version 2017-08-03
 */
public class OcrDriver extends DataEntity<OcrDriver> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录名
	private String realName;		// 姓名
	private String password;		// 密码
	private String sex;		// 性别
	private String phone;		// 联系电话
	private String duty;		// 职务(预留字段)
	private String cardNumber;		// 身份证号
	private String jobNumber;		// 驾驶员编号
	private String drivingLicenceNumber;
	private String status;		// 状态
	private Area area; //司机所在地区
	private String resetCode;		// 重置码
	
	private String newPassword;
	
	private List<OcrTask> ocrTaskList;
	
	private OcrTask ocrTask1;// 上一任务
	private OcrTask ocrTask2;// 下一任务
	
	private Date departureDate;		// 出发时间
	private Date planArrivalDate;		// 预计到达时间

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

	public OcrTask getOcrTask1() {
		return ocrTask1;
	}

	public void setOcrTask1(OcrTask ocrTask1) {
		this.ocrTask1 = ocrTask1;
	}

	public OcrTask getOcrTask2() {
		return ocrTask2;
	}

	public void setOcrTask2(OcrTask ocrTask2) {
		this.ocrTask2 = ocrTask2;
	}

	public List<OcrTask> getOcrTaskList() {
		return ocrTaskList;
	}

	public void setOcrTaskList(List<OcrTask> ocrTaskList) {
		this.ocrTaskList = ocrTaskList;
	}

	public OcrDriver() {
		super();
	}

	public OcrDriver(String id){
		super(id);
	}

	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1, max=100, message="姓名长度必须介于 1 和 100 之间")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=1, max=11, message="联系电话长度必须介于 1 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="职务(预留字段)长度必须介于 0 和 100 之间")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@Length(min=1, max=100, message="身份证号长度必须介于 1 和 100 之间")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Length(min=1, max=64, message="驾驶员编号长度必须介于 1 和 64 之间")
	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=8, message="重置码长度必须介于 0 和 8 之间")
	public String getResetCode() {
		return resetCode;
	}

	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=1, max=100, message="驾驶证编号长度必须介于 1 和 100 之间")
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	
}