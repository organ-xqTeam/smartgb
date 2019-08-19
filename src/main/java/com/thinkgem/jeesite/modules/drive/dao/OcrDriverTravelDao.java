/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverTravel;

/**
 * 行驶记录DAO接口
 * @author ybc
 * @version 2017-08-03
 */
@MyBatisDao
public interface OcrDriverTravelDao extends CrudDao<OcrDriverTravel> {
	
	OcrDriverTravel findListByCarid(@Param("carid") String carid);
	
	List<OcrDriverTravel> findListGroup();
	
}