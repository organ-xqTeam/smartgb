/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ocruser.dao;

import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ocruser.entity.OcrUser;

/**
 * 租车用户DAO接口
 * @author zr
 * @version 2017-08-02
 */
@MyBatisDao
public interface OcrUserDao extends CrudDao<OcrUser> {
	public Map<String, Object> dataStatistics();
}