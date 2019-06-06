/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.obd.dao.MessageObdDao;
import com.thinkgem.jeesite.modules.obd.entity.MessageObd;
import com.thinkgem.jeesite.modules.order.dao.OcrOrderDao;
import com.thinkgem.jeesite.modules.order.entity.OcrOrder;
import com.thinkgem.jeesite.modules.order.entity.OcrOrderVO;

/**
 * 订单管理Service
 * @author zr
 * @version 2017-08-07
 */
@Service
@Transactional(readOnly = true)
public class OcrOrderService extends CrudService<OcrOrderDao, OcrOrder> {
	@Autowired
	private MessageObdDao messageObdDao;
	
	public List<MessageObd> findObdOilList(String carId,Date startTime,Date endTime){
		
		return messageObdDao.findObdOilList(carId, startTime, endTime);
	}

	
	/**
	 * 定时任务
	 */
	//TODO zr
	@Transactional(readOnly = false)
	public void batchUpdateStatus(List<OcrOrder> list){
		dao.batchUpdateStatus(list);
	}

	public Page<OcrOrderVO> findVOPage(Page<OcrOrderVO> page, OcrOrderVO entity) {
		entity.setPage(page);
		page.setList(dao.findVOList(entity));
		return page;
	}
	public Page<OcrOrderVO> findVOPageForCompany(Page<OcrOrderVO> page, OcrOrderVO entity) {
		entity.setPage(page);
		page.setList(dao.findVOListForCompany(entity));
		return page;
	}
	public Page<OcrOrderVO> findVOListForApprove(Page<OcrOrderVO> page, OcrOrderVO entity) {
		entity.setPage(page);
		page.setList(dao.findVOListForApprove(entity));
		return page;
	}
	public OcrOrder get(String id) {
		return super.get(id);
	}
	
	public List<OcrOrder> findList(OcrOrder ocrOrder) {
		return super.findList(ocrOrder);
	}
	
	public Page<OcrOrder> findPage(Page<OcrOrder> page, OcrOrder ocrOrder) {
		return super.findPage(page, ocrOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrOrder ocrOrder) {
		super.save(ocrOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrOrder ocrOrder) {
		super.delete(ocrOrder);
	}

	public OcrOrderVO findVoById(OcrOrderVO ocrOrderVO) {
		return dao.findVoById(ocrOrderVO);
	}
	
}