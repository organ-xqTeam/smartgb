/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.driverlocation.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 司机位置Entity
 * @author zr
 * @version 2017-10-09
 */
public class OcrDriverLocation extends DataEntity<OcrDriverLocation> {
	
	private static final long serialVersionUID = 1L;
	private String driverId;		// 司机id
	private String driverLng;		// 司机位置经度
	private String driverLat;		// 司机定位纬度
	
	public OcrDriverLocation() {
		super();
	}

	public OcrDriverLocation(String id){
		super(id);
	}

	@Length(min=1, max=64, message="司机id长度必须介于 1 和 64 之间")
	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	@Length(min=0, max=32, message="司机位置经度长度必须介于 0 和 32 之间")
	public String getDriverLng() {
		return driverLng;
	}

	public void setDriverLng(String driverLng) {
		this.driverLng = driverLng;
	}
	
	@Length(min=0, max=32, message="司机定位纬度长度必须介于 0 和 32 之间")
	public String getDriverLat() {
		return driverLat;
	}

	public void setDriverLat(String driverLat) {
		this.driverLat = driverLat;
	}
	
}