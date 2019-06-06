/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cost.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 账务管理Entity
 * @author zr
 * @version 2017-08-10
 */
public class OcrCost extends DataEntity<OcrCost> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单ID
	private String paymentStatus;		// 支付状态
	private String amount;		// 金额
	private Office office;		// 单位名称
	private String clearAccountsFlag;		// 清账标识
	
	public OcrCost() {
		super();
	}

	public OcrCost(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=1, message="支付状态长度必须介于 0 和 1 之间")
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=1, message="清账标识长度必须介于 0 和 1 之间")
	public String getClearAccountsFlag() {
		return clearAccountsFlag;
	}

	public void setClearAccountsFlag(String clearAccountsFlag) {
		this.clearAccountsFlag = clearAccountsFlag;
	}
	
}