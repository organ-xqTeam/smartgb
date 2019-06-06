/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.task.dao.OcrTaskDao;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;
import com.thinkgem.jeesite.modules.task.entity.OcrTaskOrderVO;

/**
 * 任务管理Service
 * @author gaoly
 * @version 2017-08-11
 */
@Service
@Transactional(readOnly = true)
public class OcrTaskService extends CrudService<OcrTaskDao, OcrTask> {
	@Transactional(readOnly = false)
	public void batchUpdateStatus(List<OcrTask> list){
		dao.batchUpdateStatus(list);
	}
	public List<OcrTaskOrderVO> findListAndOrderStatus(OcrTask ocrTask) {
		return dao.findListAndOrderStatus(ocrTask);
	}
	
	public OcrTask getByOrderId(OcrTask ocrTask) {
		return dao.getByOrderId(ocrTask);
	}
	public OcrTask get(String id) {
		return super.get(id);
	}
	
	public List<OcrTask> findList(OcrTask ocrTask) {
		return super.findList(ocrTask);
	}
	
	public Page<OcrTask> findPage(Page<OcrTask> page, OcrTask ocrTask) {
		return super.findPage(page, ocrTask);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrTask ocrTask) {
		super.save(ocrTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrTask ocrTask) {
		super.delete(ocrTask);
	}
	
	public List<OcrTask> getOcrTaskVOListByDriver(String id) {
		return super.getOcrTaskVOListByDriver(id);
	}
	
	public List<OcrTask> getOcrTaskVOListByVehicle(String id) {
		return super.getOcrTaskVOListByVehicle(id);
	}
	
}