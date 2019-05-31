package com.thinkgem.jeesite.modules.drive.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 行驶记录
 * @author ybc
 * @since 2018-6-4
 */
public class OcrDriverTravel extends DataEntity<OcrDriverTravel>{
	
	private static final long serialVersionUID = 1L;
	private String carId;		// car_id
	private Date carTime;		// car_time
	private Double latitude;		// latitude
	private Double longitude;		// longitude
	private Integer speed;		// speed
	private Integer direction;		// direction
	private String baseStation;		// base_station
	private Integer gpsLocateStatus;		// gps_locate_status
	private Integer deviceStatus;		// device_status
	private Integer batteryVoltage;		// battery_voltage
	private Integer signalIntensity;		// signal_intensity
	private Integer analogInputOne;		// analog_input_one
	private Integer analogInputTwo;		// analog_input_two
	private Integer sequenceNumber;		// sequence_number
	private Date createTime;		// create_time
	private Date startTime;			//记录的开始时间
	private Date endTime;			//记录的结束时间
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCarTime() {
		return carTime;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public void setCarTime(Date carTime) {
		this.carTime = carTime;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
