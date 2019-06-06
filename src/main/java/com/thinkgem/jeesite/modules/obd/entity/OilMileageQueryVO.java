package com.thinkgem.jeesite.modules.obd.entity;

public class OilMileageQueryVO {
	
	private String id;
	private String carId;
	private Double oilConsumption;
	private Double mileage;
	private String years;
	private String months;
	private String days;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public Double getOilConsumption() {
		return oilConsumption;
	}
	public void setOilConsumption(Double oilConsumption) {
		this.oilConsumption = oilConsumption;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	
	
	
}
