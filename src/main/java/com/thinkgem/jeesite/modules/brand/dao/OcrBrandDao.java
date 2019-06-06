/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brand.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.brand.entity.OcrBrand;

/**
 * 车辆厂牌DAO接口
 * @author zr
 * @version 2017-12-12
 */
@MyBatisDao
public interface OcrBrandDao extends CrudDao<OcrBrand> {
	
}