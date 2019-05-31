/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drive.dao.OcrDriverWorkDao;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverWork;

/**
 * 考勤记录Service
 * @author zr
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class OcrDriverWorkService extends CrudService<OcrDriverWorkDao, OcrDriverWork> {
	
	public OcrDriverWork get(String id) {
		return super.get(id);
	}
	
	
	public List<OcrDriverWork> findList(OcrDriverWork gbClean) {
		return super.findList(gbClean);
	}
	
	public Page<OcrDriverWork> findPage(Page<OcrDriverWork> page, OcrDriverWork gbClean) {
		return super.findPage(page, gbClean);
	}

	
	@Transactional(readOnly = false)
	public void save(OcrDriverWork gbClean) {
		super.save(gbClean);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrDriverWork gbClean) {
		super.delete(gbClean);
	}
	
}