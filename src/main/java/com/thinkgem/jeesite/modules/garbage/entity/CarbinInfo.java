package com.thinkgem.jeesite.modules.garbage.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class CarbinInfo extends DataEntity<CarbinInfo>{ 

	//id
	private String id;
	
	//车辆编号
	private String carnum;
	
	//纬度
	private String latitude;
	
	//经度
	private String longitude;
	
	//创建时间
	private Date createDate;
	
	//更新时间
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarnum() {
		return carnum;
	}

	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
		
}
