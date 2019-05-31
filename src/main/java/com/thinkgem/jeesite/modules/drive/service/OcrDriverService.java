/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drive.dao.OcrDriverDao;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriver;
import com.thinkgem.jeesite.modules.journey.entity.OcrJourney;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;
import com.thinkgem.jeesite.modules.task.service.OcrTaskService;

/**
 * 司机管理Service
 * @author zr
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class OcrDriverService extends CrudService<OcrDriverDao, OcrDriver> {
	
	@Autowired
	private OcrTaskService ocrTaskService;

	public OcrDriver get(String id) {
		return super.get(id);
	}
	
	public OcrDriver getVO(String id, OcrJourney ocrJourney) {
		OcrDriver ocrDriver = super.getVO(id);
		int taskInterval = 30;// 默认任务间隔时间
		if(ocrDriver != null){
			for(OcrTask ocrTaskVO : ocrDriver.getOcrTaskList()){
				if(ocrTaskVO.getPlanArrivalDate() != null && ocrTaskVO.getDepartureDate() != null){
					// 上一任务
					if(ocrJourney.getTaskInterval() != null && !"".equals(ocrJourney.getTaskInterval())){
						taskInterval = Integer.parseInt(ocrJourney.getTaskInterval());
					}
					long currenDepartureDate = ocrJourney.getDepartureDate().getTime();
					currenDepartureDate -= taskInterval * 60 * 1000;
					Date departureDate = new Date(currenDepartureDate);
					int res = ocrTaskVO.getPlanArrivalDate().compareTo(departureDate);// 任务预计送达时间小于当前任务出发时间-时间间隔
					if(res == -1){// 前者小于后者
						if (ocrDriver.getOcrTask1() == null) {
							ocrDriver.setOcrTask1(ocrTaskVO);
						} else {
							int res2 = ocrTaskVO.getPlanArrivalDate().compareTo(ocrDriver.getOcrTask1().getPlanArrivalDate());// 任务预计送达时间大于存储预计送达时间
							if(res2 == 1){
								ocrDriver.setOcrTask1(ocrTaskVO);// 替换
							}
						}
					}
					// 下一任务
					if(ocrJourney.getTaskInterval() != null && !"".equals(ocrJourney.getTaskInterval())){
						taskInterval = Integer.parseInt(ocrJourney.getTaskInterval());
					}
					long currenPlanArrivalDate = ocrJourney.getPlanArrivalDate().getTime();
					currenPlanArrivalDate += taskInterval * 60 * 1000;
					Date planArrivalDate = new Date(currenPlanArrivalDate);
					int resPlanArrivalDate = ocrTaskVO.getDepartureDate().compareTo(planArrivalDate);// 任务出发时间大于当前任务预计送达时间+时间间隔
					if(resPlanArrivalDate == 1){// 前者大于后者
						if (ocrDriver.getOcrTask2() == null) {
							ocrDriver.setOcrTask2(ocrTaskVO);
						} else {
							int res2 = ocrTaskVO.getDepartureDate().compareTo(ocrDriver.getOcrTask2().getDepartureDate());// 任务出发时间小于存储出发时间
							if(res2 == -1){
								ocrDriver.setOcrTask2(ocrTaskVO);// 替换
							}
						}
					}
				}
			}
		}
		return ocrDriver;
	}
	
	public List<OcrDriver> findList(OcrDriver ocrDriver) {
		return super.findList(ocrDriver);
	}
	
	public Page<OcrDriver> findPage(Page<OcrDriver> page, OcrDriver ocrDriver) {
		return super.findPage(page, ocrDriver);
	}

	public Page<OcrDriver> findVOList(Page<OcrDriver> page, OcrDriver ocrDriver, OcrJourney ocrJourney) {
		Page<OcrDriver> ocrDriverPage = super.findVOList(page, ocrDriver);
		List<OcrDriver> ocrDriverList = ocrDriverPage.getList();
//		int taskInterval = 30;// 默认任务间隔时间
//		Iterator<OcrDriver> it = ocrDriverList.iterator();
//		while(it.hasNext()){
//			OcrDriver od = it.next();
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
//							int res2 = ocrTaskVO.getPlanArrivalDate().compareTo(od.getOcrTaskVO1().getPlanArrivalDate());// 任务预计送达时间大于存储预计送达时间
//							if(res2 == 1){
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
//							if(res2 == -1){
//								od.setOcrTaskVO2(ocrTaskVO);// 替换
//							}
//						}
//					}
//				}
//			}
//		}
		return ocrDriverPage.setList(ocrDriverList);
	}
	
	public Page<OcrDriver> findVOList2(Page<OcrDriver> page, OcrDriver ocrDriver, OcrJourney ocrJourney) {
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
		ocrDriver.setDepartureDate(departureDate);
		ocrDriver.setPlanArrivalDate(planArrivalDate);
		Page<OcrDriver> ocrDriverPage = super.findVOList2(page, ocrDriver);
		List<OcrDriver> ocrDriverList = ocrDriverPage.getList();
		Iterator<OcrDriver> it = ocrDriverList.iterator();
		while(it.hasNext()){
			OcrDriver od = it.next();
			List<OcrTask> ocrTaskList = ocrTaskService.getOcrTaskVOListByDriver(od.getId());
			for(OcrTask ocrTask : ocrTaskList){
				if(ocrTask.getPlanArrivalDate() != null && ocrTask.getDepartureDate() != null){
					// 上一任务
					int res = ocrTask.getPlanArrivalDate().compareTo(departureDate);// 任务预计送达时间小于当前任务出发时间-时间间隔
					if(res == -1){// 前者小于后者
						if (od.getOcrTask1() == null) {
							od.setOcrTask1(ocrTask);
						} else {
							int res2 = ocrTask.getPlanArrivalDate().compareTo(od.getOcrTask1().getPlanArrivalDate());// 任务预计送达时间大于存储预计送达时间
							if(res2 == 1){
								od.setOcrTask1(ocrTask);// 替换
							}
						}
					}
					// 下一任务
					int resPlanArrivalDate = ocrTask.getDepartureDate().compareTo(planArrivalDate);// 任务出发时间大于当前任务预计送达时间+时间间隔
					if(resPlanArrivalDate == 1){// 前者大于后者
						if (od.getOcrTask2() == null) {
							od.setOcrTask2(ocrTask);
						} else {
							int res2 = ocrTask.getDepartureDate().compareTo(od.getOcrTask2().getDepartureDate());// 任务出发时间小于存储出发时间
							if(res2 == -1){
								od.setOcrTask2(ocrTask);// 替换
							}
						}
					}
				}
			}
		}
		return ocrDriverPage.setList(ocrDriverList);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrDriver ocrDriver) {
		super.save(ocrDriver);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrDriver ocrDriver) {
		super.delete(ocrDriver);
	}
	
}