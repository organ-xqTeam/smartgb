/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.dao;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.obd.entity.MessageGps;

/**
 * GPS基本信息类DAO接口
 * @author tf
 * @version 2017-12-13
 */
@MyBatisDao
public interface MessageGpsDao extends CrudDao<MessageGps> {
	
}