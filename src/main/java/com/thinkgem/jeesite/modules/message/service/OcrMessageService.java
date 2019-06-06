/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.message.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.message.entity.OcrMessage;
import com.thinkgem.jeesite.modules.message.dao.OcrMessageDao;

/**
 * 推送管理Service
 * @author zr
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class OcrMessageService extends CrudService<OcrMessageDao, OcrMessage> {

	public OcrMessage get(String id) {
		return super.get(id);
	}
	
	public List<OcrMessage> findList(OcrMessage ocrMessage) {
		return super.findList(ocrMessage);
	}
	
	public Page<OcrMessage> findPage(Page<OcrMessage> page, OcrMessage ocrMessage) {
		return super.findPage(page, ocrMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrMessage ocrMessage) {
		super.save(ocrMessage);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrMessage ocrMessage) {
		super.delete(ocrMessage);
	}
	
}