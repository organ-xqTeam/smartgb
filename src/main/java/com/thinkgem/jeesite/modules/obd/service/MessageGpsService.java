/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil.LatLng;
import com.thinkgem.jeesite.modules.obd.dao.MessageGpsDao;
import com.thinkgem.jeesite.modules.obd.entity.MessageGps;

/**
 * GPS基本信息类Service
 * @author tf
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class MessageGpsService extends CrudService<MessageGpsDao, MessageGps> {

	public MessageGps get(String id) {
		return super.get(id);
	}
	
	public List<MessageGps> findList(MessageGps messageGps) {
		return super.findList(messageGps);
	}
	
	public Page<MessageGps> findPage(Page<MessageGps> page, MessageGps messageGps) {
		return super.findPage(page, messageGps);
	}
	
	@Transactional(readOnly = false)
	public void save(MessageGps messageGps) {
		super.save(messageGps);
	}
	
	@Transactional(readOnly = false)
	public void delete(MessageGps messageGps) {
		super.delete(messageGps);
	}
	/**
	 * 根据车机carId,及开始及结束时间，获得此时间段内的车机的gps数据
	 * @param equipmentImei
	 * @param realDepartureDate
	 * @param realArrivalDate
	 * @return
	 */
	public List<LatLng> getListByCarIdAndDate(String equipmentImei,
			Date realDepartureDate, Date realArrivalDate) {
		MessageGps gps = new MessageGps();
		gps.setCarId(equipmentImei);
		gps.setStartTime(realDepartureDate);
		gps.setEndTime(realArrivalDate);
		List<MessageGps> list = super.findList(gps);
		List<LatLng> latList = new ArrayList<LatLng>();
		//list集合中将经纬度从火星转到地图
		for(MessageGps li:list){
			LatLng lat = CoordinationConvertUtil.transformFromGCJToWGS( li.getLatitude(),li.getLongitude());
			latList.add(lat);
		}
		return latList;
	}
	
}