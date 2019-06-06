/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ocruser.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户反馈Entity
 * @author zr
 * @version 2017-08-16
 */
public class OcrFeedback extends DataEntity<OcrFeedback> {
	
	private static final long serialVersionUID = 1L;
	private String realName;		// 真实姓名
	private String phone;		// 手机号
	private String content;		// 内容
	private String status;		// 状态
	
	public OcrFeedback() {
		super();
	}

	public OcrFeedback(String id){
		super(id);
	}

	@Length(min=0, max=100, message="真实姓名长度必须介于 0 和 100 之间")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Length(min=0, max=100, message="手机号长度必须介于 0 和 100 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}