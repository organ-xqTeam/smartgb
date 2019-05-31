/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;
import com.thinkgem.jeesite.modules.task.entity.OcrTaskOrderVO;

/**
 * 任务管理DAO接口
 * @author gaoly
 * @version 2017-08-11
 */
@MyBatisDao
public interface OcrTaskDao extends CrudDao<OcrTask> {
	OcrTask getByOrderId(OcrTask entity);
	void batchUpdateStatus(List<OcrTask> list);
	List<OcrTaskOrderVO> findListAndOrderStatus(OcrTask ocrTask);
}