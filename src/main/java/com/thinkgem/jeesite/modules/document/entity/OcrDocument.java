/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.document.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 文档设置Entity
 * @author gaoly
 * @version 2017-08-15
 */
public class OcrDocument extends DataEntity<OcrDocument> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 类型【0：用户协议，1：关于我们】
	private String content;		// 内容
	
	public OcrDocument() {
		super();
	}

	public OcrDocument(String id){
		super(id);
	}

	@Length(min=0, max=1, message="类型【0：用户协议，1：关于我们】长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}