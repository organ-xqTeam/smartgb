/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ocruser.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ocruser.entity.OcrFeedback;
import com.thinkgem.jeesite.modules.ocruser.dao.OcrFeedbackDao;

/**
 * 用户反馈Service
 * @author zr
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class OcrFeedbackService extends CrudService<OcrFeedbackDao, OcrFeedback> {

	public OcrFeedback get(String id) {
		return super.get(id);
	}
	
	public List<OcrFeedback> findList(OcrFeedback ocrFeedback) {
		return super.findList(ocrFeedback);
	}
	
	public Page<OcrFeedback> findPage(Page<OcrFeedback> page, OcrFeedback ocrFeedback) {
		return super.findPage(page, ocrFeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrFeedback ocrFeedback) {
		super.save(ocrFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrFeedback ocrFeedback) {
		super.delete(ocrFeedback);
	}
	
}