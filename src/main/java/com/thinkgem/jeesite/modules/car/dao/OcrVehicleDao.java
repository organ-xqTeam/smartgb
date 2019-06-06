/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.car.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicleGPSVO;

/**
 * 车辆管理DAO接口
 * @author zr
 * @version 2017-08-03
 */
@MyBatisDao
public interface OcrVehicleDao extends CrudDao<OcrVehicle> {
	
	public List<OcrVehicleGPSVO> findOcrVehicleGPSList();
	public OcrVehicleGPSVO findOcrVehicleGPS(String id);
	public OcrVehicle fingTankType(String id);

}