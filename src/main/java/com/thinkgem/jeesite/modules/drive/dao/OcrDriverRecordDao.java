/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverRecord;

/**
 * 形式记录DAO接口
 * @author ybc
 * @version 2018-06-10
 */
@MyBatisDao
public interface OcrDriverRecordDao extends CrudDao<OcrDriverRecord> {
	
}