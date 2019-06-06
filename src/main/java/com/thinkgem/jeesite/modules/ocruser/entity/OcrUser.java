/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ocruser.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 租车用户Entity
 * @author zr
 * @version 2017-08-02
 */
public class OcrUser extends DataEntity<OcrUser> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录名
	private String realName;		// 真实姓名
	private String password;		// 密码
	private String phone;		// 手机号
	private Office office;		// 公司ID
	private String duty;		// 职务（预留字段）
	private String cardNumber;		// 身份证号
	private String jobNumber;		// 工号
	private String status;		// 状态
	private String resetCode;		// 重置码
	
	public OcrUser() {
		super();
	}

	public OcrUser(String id){
		super(id);
	}

	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1, max=100, message="真实姓名长度必须介于 1 和 100 之间")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Length(min=1, max=100, message="密码长度必须介于 1 和 32 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=1, max=100, message="手机号长度必须介于 1 和 100 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	
	@Length(min=0, max=100, message="职务（预留字段）长度必须介于 0 和 100 之间")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@Length(min=0, max=100, message="身份证号长度必须介于 0 和 100 之间")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Length(min=1, max=64, message="工号长度必须介于 1 和 64 之间")
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

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}