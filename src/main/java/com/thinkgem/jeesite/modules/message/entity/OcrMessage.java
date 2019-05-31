/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.message.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 推送管理Entity
 * @author zr
 * @version 2017-08-16
 */
public class OcrMessage extends DataEntity<OcrMessage> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String content;		// 内容
	private String receiveSide;		// 接收端
	private String status;		// 状态
	
	public OcrMessage() {
		super();
		receiveSide = "0";
	}

	public OcrMessage(String id){
		super(id);
	}

	@Length(min=0, max=100, message="标题长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="接收端长度必须介于 0 和 1 之间")
	public String getReceiveSide() {
		return receiveSide;
	}

	public void setReceiveSide(String receiveSide) {
		this.receiveSide = receiveSide;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}