/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cost.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 账务管理Entity
 * @author zr
 * @version 2017-08-10
 */
public class CompanyCostVO extends DataEntity<CompanyCostVO> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String parentId;
	private String totalAmount;
	private String paid;
	private String unpaid;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	public String getUnpaid() {
		return unpaid;
	}
	public void setUnpaid(String unpaid) {
		this.unpaid = unpaid;
	}

	
	
	
}