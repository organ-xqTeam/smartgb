/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil.LatLng;
import com.thinkgem.jeesite.modules.drive.dao.OcrDriverTravelDao;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverRecord;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverTravel;
import com.thinkgem.jeesite.modules.obd.entity.MessageGps;

/**
 * 行驶记录Service
 * @author ybc
 * @version 2018-06-14
 */
@Service
@Transactional(readOnly = true)
public class OcrDriverTravelService extends CrudService<OcrDriverTravelDao, OcrDriverTravel> {
	
	@Autowired
	private OcrDriverTravelDao dao;
	
	public OcrDriverTravel get(String id) {
		return super.get(id);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrDriverTravel travel) {
		super.save(travel);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrDriverTravel travel) {
		super.delete(travel);
	}
	
	@Transactional(readOnly = false)
	public List<OcrDriverTravel> getTravelList() {
		List<OcrDriverTravel> carList = dao.findListGroup();
		List<OcrDriverTravel> result = new ArrayList<OcrDriverTravel>();
		for(OcrDriverTravel c : carList) {
			result.add(dao.findListByCarid(c.getCarId()));
		}
		return result;
	}

	public List<LatLng> getListByCarIdAndDate(String equipmentImei, Date goDate, Date tomorrowDate) {
		OcrDriverTravel gps = new OcrDriverTravel();
		gps.setCarId(equipmentImei);
		gps.setStartTime(goDate);
		gps.setEndTime(tomorrowDate);
		List<OcrDriverTravel> list = super.findList(gps);
		List<LatLng> latList = new ArrayList<LatLng>();
		//list集合中将经纬度从火星转到地图
		for(OcrDriverTravel li:list){
			//LatLng lat = CoordinationConvertUtil.transformFromGCJToWGS(li.getLatitude(),li.getLongitude());
			LatLng lat = new LatLng(li.getLatitude(),li.getLongitude());
			latList.add(lat);
		}
		return latList;
	
	}
	
}