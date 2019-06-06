/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cost.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 账务管理Entity
 * @author zr
 * @version 2017-08-10
 */
public class OcrCostDetailedVO extends DataEntity<OcrCostDetailedVO> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String companyId;
	private String status;//清账标识，清账0，未清账1
	private String year;		// 年
	private String month;		// 月
	private String orderCount;
	private BigDecimal totalAmount;		//总金额 
	private BigDecimal unpaid;		// 未支付
	private BigDecimal paidOnline;		// 在线已支付
	private BigDecimal unpaidOnline;		// 在线未支付
	private BigDecimal paidUnified;		// 统一支付
	private String beginDepartureDate;		// 开始 出发时间
	private String endDepartureDate;		
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public BigDecimal getPaidOnline() {
		return paidOnline;
	}
	public void setPaidOnline(BigDecimal paidOnline) {
		this.paidOnline = paidOnline;
	}
	public BigDecimal getUnpaidOnline() {
		return unpaidOnline;
	}
	public void setUnpaidOnline(BigDecimal unpaidOnline) {
		this.unpaidOnline = unpaidOnline;
	}
	public BigDecimal getPaidUnified() {
		return paidUnified;
	}
	public void setPaidUnified(BigDecimal paidUnified) {
		this.paidUnified = paidUnified;
	}
	public BigDecimal getUnpaid() {
		return unpaid;
	}
	public void setUnpaid(BigDecimal unpaid) {
		this.unpaid = unpaid;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBeginDepartureDate() {
		return beginDepartureDate;
	}

	public void setBeginDepartureDate(String beginDepartureDate) {
		this.beginDepartureDate = beginDepartureDate;
	}
	
	public String getEndDepartureDate() {
		return endDepartureDate;
	}

	public void setEndDepartureDate(String endDepartureDate) {
		this.endDepartureDate = endDepartureDate;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}

	
}