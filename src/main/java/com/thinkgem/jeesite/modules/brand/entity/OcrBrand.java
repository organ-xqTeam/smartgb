/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brand.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车辆厂牌Entity
 * @author zr
 * @version 2017-12-12
 */
public class OcrBrand extends DataEntity<OcrBrand> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleBrand;		// 车辆厂牌
	private String tankType;		// 油箱类型【0：L，1：%】
	
	public OcrBrand() {
		super();
	}

	public OcrBrand(String id){
		super(id);
	}

	@Length(min=0, max=100, message="车辆厂牌长度必须介于 0 和 100 之间")
	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	
	@Length(min=0, max=1, message="油箱类型【0：L，1：%】长度必须介于 0 和 1 之间")
	public String getTankType() {
		return tankType;
	}

	public void setTankType(String tankType) {
		this.tankType = tankType;
	}
	
}