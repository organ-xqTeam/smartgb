/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.journey.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.journey.entity.OcrJourney;
import com.thinkgem.jeesite.modules.journey.dao.OcrJourneyDao;

/**
 * 行程管理Service
 * @author gaoly
 * @version 2017-08-09
 */
@Service
@Transactional(readOnly = true)
public class OcrJourneyService extends CrudService<OcrJourneyDao, OcrJourney> {

	public OcrJourney get(String id) {
		return super.get(id);
	}
	
	public List<OcrJourney> findList(OcrJourney ocrJourney) {
		return super.findList(ocrJourney);
	}
	
	public Page<OcrJourney> findPage(Page<OcrJourney> page, OcrJourney ocrJourney) {
		return super.findPage(page, ocrJourney);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrJourney ocrJourney) {
		super.save(ocrJourney);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrJourney ocrJourney) {
		super.delete(ocrJourney);
	}
	
}