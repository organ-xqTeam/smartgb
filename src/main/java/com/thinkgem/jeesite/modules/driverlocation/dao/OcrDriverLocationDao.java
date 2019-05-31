/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.driverlocation.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.driverlocation.entity.OcrDriverLocation;

/**
 * 司机位置DAO接口
 * @author zr
 * @version 2017-10-09
 */
@MyBatisDao
public interface OcrDriverLocationDao extends CrudDao<OcrDriverLocation> {
	
}