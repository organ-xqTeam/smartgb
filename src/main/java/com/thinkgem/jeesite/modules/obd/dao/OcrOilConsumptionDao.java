/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.obd.entity.OcrOilConsumption;
import com.thinkgem.jeesite.modules.obd.entity.OilMileageQueryVO;

/**
 * 油量消耗DAO接口
 * @author tf
 * @version 2017-12-15
 */
@MyBatisDao
public interface OcrOilConsumptionDao extends CrudDao<OcrOilConsumption> {

	int insertList(List<OcrOilConsumption> ocrOilConsumptionList);
	/**
	 * 查询月油耗
	 * @param id
	 * @param year
	 * @return
	 */
	List<OilMileageQueryVO> monthOilConsumptionList (@Param("id")String id,@Param("year") String year);
	/**
	 * 查询每日油耗和公里数
	 */
	List<OcrOilConsumption> dayOilAndMileageConsumptionList (@Param("id")String id,@Param("year") String year,@Param("month") String month);
}