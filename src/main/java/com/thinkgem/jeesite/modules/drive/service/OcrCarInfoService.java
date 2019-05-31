/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drive.dao.OcrCarInfoDao;
import com.thinkgem.jeesite.modules.drive.entity.OcrCarInfo;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriver;

/**
 * 车辆信息管理Service
 * @author ybc
 * @version 2018-08-03
 */
@Service
@Transactional(readOnly = true)
public class OcrCarInfoService extends CrudService<OcrCarInfoDao, OcrCarInfo> {
	
	public OcrCarInfo get(String id) {
		return super.get(id);
	}
	
	public List<OcrCarInfo> findList(OcrCarInfo ocrCarInfo) {
		return super.findList(ocrCarInfo);
	}
	
	public Page<OcrCarInfo> findPage(Page<OcrCarInfo> page, OcrCarInfo ocrCarInfo) {
		return super.findPage(page, ocrCarInfo);
	}

	
	@Transactional(readOnly = false)
	public void save(OcrCarInfo ocrCarInfo) {
		ocrCarInfo.setCreateDate(new Date());
		super.save(ocrCarInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrCarInfo ocrCarInfo) {
		super.delete(ocrCarInfo);
	}
	
}