/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.driverlocation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.driverlocation.entity.OcrDriverLocation;
import com.thinkgem.jeesite.modules.driverlocation.dao.OcrDriverLocationDao;

/**
 * 司机位置Service
 * @author zr
 * @version 2017-10-09
 */
@Service
@Transactional(readOnly = true)
public class OcrDriverLocationService extends CrudService<OcrDriverLocationDao, OcrDriverLocation> {

	public OcrDriverLocation get(String id) {
		return super.get(id);
	}
	
	public List<OcrDriverLocation> findList(OcrDriverLocation ocrDriverLocation) {
		return super.findList(ocrDriverLocation);
	}
	
	public Page<OcrDriverLocation> findPage(Page<OcrDriverLocation> page, OcrDriverLocation ocrDriverLocation) {
		return super.findPage(page, ocrDriverLocation);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrDriverLocation ocrDriverLocation) {
		super.save(ocrDriverLocation);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrDriverLocation ocrDriverLocation) {
		super.delete(ocrDriverLocation);
	}
	
}