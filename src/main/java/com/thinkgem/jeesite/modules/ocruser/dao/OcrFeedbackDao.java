/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ocruser.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ocruser.entity.OcrFeedback;

/**
 * 用户反馈DAO接口
 * @author zr
 * @version 2017-08-16
 */
@MyBatisDao
public interface OcrFeedbackDao extends CrudDao<OcrFeedback> {
	
}