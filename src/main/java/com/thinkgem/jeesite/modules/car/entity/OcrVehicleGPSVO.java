/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.car.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.drive.entity.OcrTaskVO;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;

/**
 * 车辆GPS信息
 * @author zr
 * @version 2017-08-03
 */
public class OcrVehicleGPSVO extends DataEntity<OcrVehicleGPSVO> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleBrand;		// 车辆厂牌
	private String tankType;  //邮箱类型0：L  1:%
	private String brandModels;		// 品牌车型
	private String vehicleType;		// 车辆类型
	private String licensePlateNumber;		// 车牌号
	private String drivingLicenseNumber;		// 行驶证编号
	private String engineNumber;		// 发动机编号
	private Area area; //车辆所在地区
	private String vehicleIdentificationNumber;		// 车架号
	private String tankVolume;		// 油箱容积(L)
	private String equipmentImei;		// 设备IMEI
	private String equipmentStatus;		// 设备绑定状态
	private String seatCount;		// 座位数量
	private String status;		// 状态
	
	
	private String carId;		// car_id
	private Date carTime;		// car_time
	private String latitude;		// 纬度
	private String longitude;		// 经度
	private Integer speed;		// 速度
	private Integer direction;		// 方向 0-北
	private String baseStation;		// 基站
	private Integer gpsLocateStatus;		// 定位状态 0：未定位 1：已定位
	private Integer deviceStatus;		// device_status
	private Integer batteryVoltage;		// battery_voltage
	private Integer signalIntensity;		// signal_intensity
	private Integer analogInputOne;		// analog_input_one
	private Integer analogInputTwo;		// analog_input_two
	private Integer sequenceNumber;		// sequence_number
	private Date createTime;		// create_time


	
	public OcrVehicleGPSVO() {
		super();
	}

	public OcrVehicleGPSVO(String id){
		super(id);
	}

	@Length(min=0, max=100, message="车辆厂牌长度必须介于 0 和 100之间")
	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	@Length(min=1, max=100, message="品牌车型长度必须介于 1 和 10 之间")
	public String getBrandModels() {
		return brandModels;
	}

	public void setBrandModels(String brandModels) {
		this.brandModels = brandModels;
	}
	
	@Length(min=1, max=100, message="车辆类型长度必须介于 1 和 100 之间")
	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Length(min=1, max=100, message="车牌号长度必须介于 1 和 100 之间")
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}
	
	@Length(min=1, max=100, message="行驶证编号长度必须介于 1 和 100 之间")
	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}
	
	@Length(min=1, max=11, message="发动机编号长度必须介于 1 和 11 之间")
	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	
	@Length(min=1, max=100, message="车架号长度必须介于 1 和 100 之间")
	public String getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}
	
	@Length(min=0, max=11, message="座位数量长度必须介于 0 和 11 之间")
	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	@Length(min=0, max=11, message="油箱容积(L)长度必须介于 0 和 11 之间")
	public String getTankVolume() {
		return tankVolume;
	}

	public void setTankVolume(String tankVolume) {
		this.tankVolume = tankVolume;
	}
	
	@Length(min=0, max=20, message="设备IMEI长度必须介于 0 和 20 之间")
	public String getEquipmentImei() {
		return equipmentImei;
	}

	public void setEquipmentImei(String equipmentImei) {
		this.equipmentImei = equipmentImei;
	}
	
	@Length(min=0, max=1, message="设备绑定状态长度必须介于 0 和 1 之间")
	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Date getCarTime() {
		return carTime;
	}

	public void setCarTime(Date carTime) {
		this.carTime = carTime;
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

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getBaseStation() {
		return baseStation;
	}

	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation;
	}

	public Integer getGpsLocateStatus() {
		return gpsLocateStatus;
	}

	public void setGpsLocateStatus(Integer gpsLocateStatus) {
		this.gpsLocateStatus = gpsLocateStatus;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Integer getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Integer batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Integer getSignalIntensity() {
		return signalIntensity;
	}

	public void setSignalIntensity(Integer signalIntensity) {
		this.signalIntensity = signalIntensity;
	}

	public Integer getAnalogInputOne() {
		return analogInputOne;
	}

	public void setAnalogInputOne(Integer analogInputOne) {
		this.analogInputOne = analogInputOne;
	}

	public Integer getAnalogInputTwo() {
		return analogInputTwo;
	}

	public void setAnalogInputTwo(Integer analogInputTwo) {
		this.analogInputTwo = analogInputTwo;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTankType() {
		return tankType;
	}

	public void setTankType(String tankType) {
		this.tankType = tankType;
	}
	

}