/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.billing.entity.OcrBilling;
import com.thinkgem.jeesite.modules.billing.dao.OcrBillingDao;

/**
 * 计费规则Service
 * @author zr
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class OcrBillingService extends CrudService<OcrBillingDao, OcrBilling> {
	
	public OcrBilling selectOcrBillingNew(){
		return dao.selectOcrBillingNew();
	}
	public OcrBilling get(String id) {
		return super.get(id);
	}
	
	public List<OcrBilling> findList(OcrBilling ocrBilling) {
		return super.findList(ocrBilling);
	}
	
	public Page<OcrBilling> findPage(Page<OcrBilling> page, OcrBilling ocrBilling) {
		return super.findPage(page, ocrBilling);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrBilling ocrBilling) {
		super.save(ocrBilling);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrBilling ocrBilling) {
		super.delete(ocrBilling);
	}
	
}