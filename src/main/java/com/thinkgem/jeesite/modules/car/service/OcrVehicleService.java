/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.car.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.car.dao.OcrVehicleDao;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicleGPSVO;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle.OilCosumptionForMonth;
import com.thinkgem.jeesite.modules.journey.entity.OcrJourney;
import com.thinkgem.jeesite.modules.obd.dao.MessageObdDao;
import com.thinkgem.jeesite.modules.obd.dao.OcrOilConsumptionDao;
import com.thinkgem.jeesite.modules.obd.entity.MessageObd;
import com.thinkgem.jeesite.modules.obd.entity.OcrOilConsumption;
import com.thinkgem.jeesite.modules.obd.entity.OilMileageQueryVO;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;
import com.thinkgem.jeesite.modules.task.service.OcrTaskService;

/**
 * 车辆管理Service
 * @author zr
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class OcrVehicleService extends CrudService<OcrVehicleDao, OcrVehicle> {
	
	@Autowired
	private OcrTaskService ocrTaskService;
	
	@Autowired
	private MessageObdDao messageObdDao;
	
	@Autowired
	private OcrOilConsumptionDao ocrOilConsumptionDao;

	public OcrVehicle get(String id) {
		return super.get(id);
	}
	
	public List<OcrVehicle> findList(OcrVehicle ocrVehicle) {
		return super.findList(ocrVehicle);
	}
	
	public Page<OcrVehicle> findPage(Page<OcrVehicle> page, OcrVehicle ocrVehicle) {
		return super.findPage(page, ocrVehicle);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrVehicle ocrVehicle) {
		super.save(ocrVehicle);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrVehicle ocrVehicle) {
		super.delete(ocrVehicle);
	}
	
	@Transactional(readOnly = false)
	public Page<OcrVehicle> findVOList(Page<OcrVehicle> page, OcrVehicle ocrVehicle, OcrJourney ocrJourney) {
		Page<OcrVehicle> ocrVehiclePage = super.findVOList(page, ocrVehicle);
		List<OcrVehicle> ocrVehicleList = ocrVehiclePage.getList();
//		int taskInterval = 30;// 默认任务间隔时间
//		Iterator<OcrVehicle> it = ocrVehicleList.iterator();
//		while(it.hasNext()){
//			OcrVehicle od = it.next();
//			for(OcrTaskVO ocrTaskVO : od.getOcrTaskVOList()){
//				if(ocrTaskVO.getDepartureDate() != null && ocrTaskVO.getPlanArrivalDate() != null && !"6".equals(ocrTaskVO.getStatus())){
//					if ((ocrTaskVO.getDepartureDate().after(ocrJourney.getDepartureDate())
//							&& ocrTaskVO.getDepartureDate().before(ocrJourney.getPlanArrivalDate()))
//							|| (ocrTaskVO.getPlanArrivalDate().after(ocrJourney.getDepartureDate())
//									&& ocrTaskVO.getPlanArrivalDate().before(ocrJourney.getPlanArrivalDate()))) {
//						it.remove();
//						break;
//					}
//					// 上一任务
//					if(ocrJourney.getTaskInterval() != null && !"".equals(ocrJourney.getTaskInterval())){
//						taskInterval = Integer.parseInt(ocrJourney.getTaskInterval());
//					}
//					long currenDepartureDate = ocrJourney.getDepartureDate().getTime();
//					currenDepartureDate -= taskInterval * 60 * 1000;
//					Date departureDate = new Date(currenDepartureDate);
//					int res = ocrTaskVO.getPlanArrivalDate().compareTo(departureDate);// 任务预计送达时间小于当前任务出发时间-时间间隔
//					if(res == -1){// 前者小于后者
//						if (od.getOcrTaskVO1() == null) {
//							od.setOcrTaskVO1(ocrTaskVO);
//						} else {
//							int res2 = ocrTaskVO.getPlanArrivalDate()
//									.compareTo(od.getOcrTaskVO1().getPlanArrivalDate());// 任务预计送达时间大于存储预计送达时间
//							if (res2 == 1) {
//								od.setOcrTaskVO1(ocrTaskVO);// 替换
//							}
//						}
//					}
//					// 下一任务
//					if(ocrJourney.getTaskInterval() != null && !"".equals(ocrJourney.getTaskInterval())){
//						taskInterval = Integer.parseInt(ocrJourney.getTaskInterval());
//					}
//					long currenPlanArrivalDate = ocrJourney.getPlanArrivalDate().getTime();
//					currenPlanArrivalDate += taskInterval * 60 * 1000;
//					Date planArrivalDate = new Date(currenPlanArrivalDate);
//					int resPlanArrivalDate = ocrTaskVO.getDepartureDate().compareTo(planArrivalDate);// 任务出发时间大于当前任务预计送达时间+时间间隔
//					if(resPlanArrivalDate == 1){// 前者大于后者
//						if (od.getOcrTaskVO2() == null) {
//							od.setOcrTaskVO2(ocrTaskVO);
//						} else {
//							int res2 = ocrTaskVO.getDepartureDate().compareTo(od.getOcrTaskVO2().getDepartureDate());// 任务出发时间小于存储出发时间
//							if (res2 == -1) {
//								od.setOcrTaskVO2(ocrTaskVO);// 替换
//							}
//						}
//					}
//				}
//			}
//		}
		return ocrVehiclePage.setList(ocrVehicleList);
	}
	
	@Transactional(readOnly = false)
	public Page<OcrVehicle> findVOList2(Page<OcrVehicle> page, OcrVehicle ocrVehicle, OcrJourney ocrJourney) {
		int taskInterval = 30;// 默认任务间隔时间
		if(ocrJourney.getTaskInterval() != null && !"".equals(ocrJourney.getTaskInterval())){
			taskInterval = Integer.parseInt(ocrJourney.getTaskInterval());
		}
		long currenDepartureDate = ocrJourney.getDepartureDate().getTime();
		currenDepartureDate -= taskInterval * 60 * 1000;
		Date departureDate = new Date(currenDepartureDate);
		long currenPlanArrivalDate = ocrJourney.getPlanArrivalDate().getTime();
		currenPlanArrivalDate += taskInterval * 60 * 1000;
		Date planArrivalDate = new Date(currenPlanArrivalDate);
		ocrVehicle.setDepartureDate(departureDate);
		ocrVehicle.setPlanArrivalDate(planArrivalDate);
		Page<OcrVehicle> ocrVehiclePage = super.findVOList(page, ocrVehicle);
		List<OcrVehicle> ocrVehicleList = ocrVehiclePage.getList();
		Iterator<OcrVehicle> it = ocrVehicleList.iterator();
		while(it.hasNext()){
			OcrVehicle ov = it.next();
			List<OcrTask> ocrTaskList = ocrTaskService.getOcrTaskVOListByVehicle(ov.getId());
			for(OcrTask ocrTask : ocrTaskList){
				if(ocrTask.getPlanArrivalDate() != null && ocrTask.getDepartureDate() != null){
					// 上一任务
					int res = ocrTask.getPlanArrivalDate().compareTo(departureDate);// 任务预计送达时间小于当前任务出发时间-时间间隔
					if(res == -1){// 前者小于后者
						if (ov.getOcrTask1() == null) {
							ov.setOcrTask1(ocrTask);
						} else {
							int res2 = ocrTask.getPlanArrivalDate().compareTo(ov.getOcrTask1().getPlanArrivalDate());// 任务预计送达时间大于存储预计送达时间
							if(res2 == 1){
								ov.setOcrTask1(ocrTask);// 替换
							}
						}
					}
					// 下一任务
					int resPlanArrivalDate = ocrTask.getDepartureDate().compareTo(planArrivalDate);// 任务出发时间大于当前任务预计送达时间+时间间隔
					if(resPlanArrivalDate == 1){// 前者大于后者
						if (ov.getOcrTask2() == null) {
							ov.setOcrTask2(ocrTask);
						} else {
							int res2 = ocrTask.getDepartureDate().compareTo(ov.getOcrTask2().getDepartureDate());// 任务出发时间小于存储出发时间
							if(res2 == -1){
								ov.setOcrTask2(ocrTask);// 替换
							}
						}
					}
				}
			}
		}
		return ocrVehiclePage.setList(ocrVehicleList);
	}
	
	@Transactional(readOnly = false)
	public Page<OcrVehicle> findVOList3(Page<OcrVehicle> page, OcrVehicle ocrVehicle, OcrJourney ocrJourney) {
		return null;
	}

	/**
	 * 通过车机id获得月油耗及里程实体
	 * @param equipmentImei
	 * @return
	 */
	public List<OilCosumptionForMonth> getOilCosumptionForMonth(
			String id,String year) {
		List<OilCosumptionForMonth> list = Lists.newArrayList();
		
		
		//查询每月油耗
		List<OilMileageQueryVO> oilVOs = ocrOilConsumptionDao.monthOilConsumptionList(id, year);
		
		OcrVehicle OcrVehicle = dao.fingTankType(id);
		if ("1".equals(OcrVehicle.getTankType())){
			for (OilMileageQueryVO oilMileageQueryVO : oilVOs) {
				BigDecimal b = new BigDecimal (oilMileageQueryVO.getOilConsumption()*(Double.parseDouble(OcrVehicle.getTankVolume()))/100);
				double oilVoluem = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
				oilMileageQueryVO.setOilConsumption(oilVoluem);
			}
		}
		
		//查询每月公里数
		List<OilMileageQueryVO> mileageVOs = messageObdDao.monthMileageList(id, year);
		
				for (int i = 1; i < 13; i++) {
					OilCosumptionForMonth cosumptionForMonth = new OilCosumptionForMonth();
					cosumptionForMonth.setMonth(String.valueOf(i));
					cosumptionForMonth.setYear(year);
					//添加每月油耗
					for (OilMileageQueryVO oilMileageQueryVO : oilVOs) {
						if (String.valueOf(i).equals(oilMileageQueryVO.getMonths())) {
							cosumptionForMonth.setOilConsumptionForMonth(oilMileageQueryVO.getOilConsumption());
						}
					}
					//添加每月公里数
					for (OilMileageQueryVO oilMileageQueryVO : mileageVOs) {
						if (String.valueOf(i).equals(oilMileageQueryVO.getMonths())) {
							cosumptionForMonth.setDrivingMileage(oilMileageQueryVO.getMileage());
						}
					}
					//计算每百公里耗油
					if (cosumptionForMonth.getOilConsumptionForMonth()!=null&&cosumptionForMonth.getDrivingMileage()!=null) {
						BigDecimal b = new BigDecimal(cosumptionForMonth.getOilConsumptionForMonth()/cosumptionForMonth.getDrivingMileage()*100);  
						double ConsumptionForHundredKMS = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
						cosumptionForMonth.setOilConsumptionForHundredKMS(ConsumptionForHundredKMS);
					}else{
						cosumptionForMonth.setOilConsumptionForHundredKMS(0.00);	
					}
					
					if (cosumptionForMonth.getOilConsumptionForMonth()==null) {
						cosumptionForMonth.setOilConsumptionForMonth(0.00);
					}
					if (cosumptionForMonth.getDrivingMileage()==null) {
						cosumptionForMonth.setDrivingMileage(0.00);
					}
					list.add(cosumptionForMonth);
				}

		
		return list;
	}
	
	
	public List<OcrVehicleGPSVO> findOcrVehicleGPSList(){
		return dao.findOcrVehicleGPSList();
	}
	public OcrVehicleGPSVO findOcrVehicleGPS(String id){
		return dao.findOcrVehicleGPS(id);
	}
	
	public List<MessageObd> findObdItemById(String id){
		return messageObdDao.findObdItemById(id);
	}
	public List<OcrOilConsumption> dayOilAndMileageConsumptionList (String id, String year, String month){
		List<OcrOilConsumption> list = ocrOilConsumptionDao.dayOilAndMileageConsumptionList(id, year, month);
		
		OcrVehicle OcrVehicle = dao.fingTankType(id);
		if ("1".equals(OcrVehicle.getTankType())){
			for (OcrOilConsumption ocrOilConsumption : list) {
				BigDecimal b = new BigDecimal (ocrOilConsumption.getOilConsumptionDay()*(Double.parseDouble(OcrVehicle.getTankVolume()))/100);
				double oilVoluem = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
				ocrOilConsumption.setOilConsumptionDay(oilVoluem);
			}
		}
		
		
		return list;
		
	}
	public OcrVehicle fingTankType(String id){
		return dao.fingTankType(id);
	}

	
}