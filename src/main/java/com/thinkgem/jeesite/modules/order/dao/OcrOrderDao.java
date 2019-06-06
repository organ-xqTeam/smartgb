/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.order.entity.OcrOrder;
import com.thinkgem.jeesite.modules.order.entity.OcrOrderVO;

/**
 * 订单管理DAO接口
 * @author zr
 * @version 2017-08-07
 */
@MyBatisDao
public interface OcrOrderDao extends CrudDao<OcrOrder> {
	
	public List<OcrOrderVO> findVOList(OcrOrderVO entity);
	public List<OcrOrderVO> findVOListForCompany(OcrOrderVO entity);
	public List<OcrOrderVO> findVOListForApprove(OcrOrderVO entity);
	void batchUpdateStatus(List<OcrOrder> list);
	public OcrOrderVO findVoById(OcrOrderVO ocrOrderVO);


}