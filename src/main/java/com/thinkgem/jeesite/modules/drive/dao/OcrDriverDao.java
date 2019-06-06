/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriver;

/**
 * 司机管理DAO接口
 * @author zr
 * @version 2017-08-03
 */
@MyBatisDao
public interface OcrDriverDao extends CrudDao<OcrDriver> {
	
}