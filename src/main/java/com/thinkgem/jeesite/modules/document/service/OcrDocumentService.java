/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.document.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.document.entity.OcrDocument;
import com.thinkgem.jeesite.modules.document.dao.OcrDocumentDao;

/**
 * 文档设置Service
 * @author gaoly
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class OcrDocumentService extends CrudService<OcrDocumentDao, OcrDocument> {

	public OcrDocument get(String id) {
		return super.get(id);
	}
	
	public List<OcrDocument> findList(OcrDocument ocrDocument) {
		return super.findList(ocrDocument);
	}
	
	public Page<OcrDocument> findPage(Page<OcrDocument> page, OcrDocument ocrDocument) {
		return super.findPage(page, ocrDocument);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrDocument ocrDocument) {
		super.save(ocrDocument);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrDocument ocrDocument) {
		super.delete(ocrDocument);
	}
	
}