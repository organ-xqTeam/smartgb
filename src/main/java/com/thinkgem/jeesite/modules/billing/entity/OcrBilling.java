/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.billing.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 计费规则Entity
 * @author zr
 * @version 2017-08-16
 */
public class OcrBilling extends DataEntity<OcrBilling> {
	
	private static final long serialVersionUID = 1L;
	private String ruleCoefficient;		// 规则系数
	
	public OcrBilling() {
		super();
	}

	public OcrBilling(String id){
		super(id);
	}

	public String getRuleCoefficient() {
		return ruleCoefficient;
	}

	public void setRuleCoefficient(String ruleCoefficient) {
		this.ruleCoefficient = ruleCoefficient;
	}
	
}