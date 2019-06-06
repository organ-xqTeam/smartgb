/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.journey.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.journey.entity.OcrJourney;

/**
 * 行程管理DAO接口
 * @author gaoly
 * @version 2017-08-09
 */
@MyBatisDao
public interface OcrJourneyDao extends CrudDao<OcrJourney> {
	
}