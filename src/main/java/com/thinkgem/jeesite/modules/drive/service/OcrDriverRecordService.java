/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drive.dao.OcrDriverRecordDao;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverRecord;

/**
 * 油耗记录Service
 * @author ybc
 * @version 2018-06-14
 */
@Service
@Transactional(readOnly = true)
public class OcrDriverRecordService extends CrudService<OcrDriverRecordDao, OcrDriverRecord> {
	
	public OcrDriverRecord get(String id) {
		return super.get(id);
	}
	
	
	public List<OcrDriverRecord> findList(OcrDriverRecord record) {
		return super.findList(record);
	}
	
	public Page<OcrDriverRecord> findPage(Page<OcrDriverRecord> page, OcrDriverRecord record) {
		return super.findPage(page, record);
	}

	
	@Transactional(readOnly = false)
	public void save(OcrDriverRecord record) {
		super.save(record);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrDriverRecord record) {
		super.delete(record);
	}
	
	public OcrDriverRecord getRecordByCarId(String carId) {
		return super.getVO(carId);
	} 
}