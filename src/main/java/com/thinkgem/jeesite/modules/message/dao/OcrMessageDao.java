/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.message.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.message.entity.OcrMessage;

/**
 * 推送管理DAO接口
 * @author zr
 * @version 2017-08-16
 */
@MyBatisDao
public interface OcrMessageDao extends CrudDao<OcrMessage> {
	
}