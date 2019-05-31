/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * gps信息Entity
 * @author tf
 * @version 2017-12-13
 */
public class MessageGps extends DataEntity<MessageGps> {
	
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
	private Date startTime;         //记录的开始时间
	private Date endTime;         //记录的结束时间
	
	public MessageGps() {
		super();
	}

	public MessageGps(String id){
		super(id);
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="car_time不能为空")
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
	
	@NotNull(message="latitude不能为空")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	@NotNull(message="longitude不能为空")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@NotNull(message="speed不能为空")
	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	@NotNull(message="direction不能为空")
	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	
	@Length(min=1, max=18, message="base_station长度必须介于 1 和 18 之间")
	public String getBaseStation() {
		return baseStation;
	}

	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation;
	}
	
	@NotNull(message="gps_locate_status不能为空")
	public Integer getGpsLocateStatus() {
		return gpsLocateStatus;
	}

	public void setGpsLocateStatus(Integer gpsLocateStatus) {
		this.gpsLocateStatus = gpsLocateStatus;
	}
	
	@NotNull(message="device_status不能为空")
	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	
	@NotNull(message="battery_voltage不能为空")
	public Integer getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Integer batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	
	@NotNull(message="signal_intensity不能为空")
	public Integer getSignalIntensity() {
		return signalIntensity;
	}

	public void setSignalIntensity(Integer signalIntensity) {
		this.signalIntensity = signalIntensity;
	}
	
	@NotNull(message="analog_input_one不能为空")
	public Integer getAnalogInputOne() {
		return analogInputOne;
	}

	public void setAnalogInputOne(Integer analogInputOne) {
		this.analogInputOne = analogInputOne;
	}
	
	@NotNull(message="analog_input_two不能为空")
	public Integer getAnalogInputTwo() {
		return analogInputTwo;
	}

	public void setAnalogInputTwo(Integer analogInputTwo) {
		this.analogInputTwo = analogInputTwo;
	}
	
	@NotNull(message="sequence_number不能为空")
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="create_time不能为空")
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