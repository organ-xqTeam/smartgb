/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ocruser.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ocruser.entity.OcrUser;
import com.thinkgem.jeesite.modules.ocruser.dao.OcrUserDao;

/**
 * 租车用户Service
 * @author zr
 * @version 2017-08-02
 */
@Service
@Transactional(readOnly = true)
public class OcrUserService extends CrudService<OcrUserDao, OcrUser> {

	public Map<String, Object> dataStatistics(){
		return dao.dataStatistics();
		
	}

	public OcrUser get(String id) {
		return super.get(id);
	}
	
	public List<OcrUser> findList(OcrUser ocrUser) {
		return super.findList(ocrUser);
	}
	
	public Page<OcrUser> findPage(Page<OcrUser> page, OcrUser ocrUser) {
		return super.findPage(page, ocrUser);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrUser ocrUser) {
		super.save(ocrUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrUser ocrUser) {
		super.delete(ocrUser);
	}
	
}