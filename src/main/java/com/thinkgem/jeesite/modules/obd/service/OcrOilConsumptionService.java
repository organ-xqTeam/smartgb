/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.obd.dao.MessageObdDao;
import com.thinkgem.jeesite.modules.obd.dao.OcrOilConsumptionDao;
import com.thinkgem.jeesite.modules.obd.entity.MessageObd;
import com.thinkgem.jeesite.modules.obd.entity.OcrOilConsumption;
import com.thinkgem.jeesite.modules.obd.entity.OilMileageQueryVO;

/**
 * 油量消耗Service
 * @author tf
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class OcrOilConsumptionService extends CrudService<OcrOilConsumptionDao, OcrOilConsumption> {
	
	@Autowired
	private MessageObdDao odbDao;
	
	@Autowired
	private OcrOilConsumptionDao ocrOilConsumptionDao;
	//两次剩余油量的最大差值，高于则认为有加油的可能
	private Double OIL_Consumption_Differ =15D;
	
	public OcrOilConsumption get(String id) {
		return super.get(id);
	}
	
	public List<OcrOilConsumption> findList(OcrOilConsumption ocrOilConsumption) {
		return super.findList(ocrOilConsumption);
	}
	
	public Page<OcrOilConsumption> findPage(Page<OcrOilConsumption> page, OcrOilConsumption ocrOilConsumption) {
		return super.findPage(page, ocrOilConsumption);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrOilConsumption ocrOilConsumption) {
		super.save(ocrOilConsumption);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrOilConsumption ocrOilConsumption) {
		super.delete(ocrOilConsumption);
	}
		/**
		 * 
		 * @param catId
		 * @param start
		 * @param end
		 */
	    @Transactional(readOnly = false)
		public Double getOilConsumption(Long catId, Date start,
				Date end) {
			//获取昨日日期
			Date date = new Date();
			Calendar calendar = new GregorianCalendar(); 
		    calendar.setTime(date); 
		    calendar.add(Calendar.DATE,-1);
		    date = calendar.getTime();
		    
			if(null==start && null == end){
				start = DateUtils.parseDate(DateUtils.formatDate(date) + " 00:00:00");
				end = DateUtils.parseDate(DateUtils.formatDate(date) + " 23:59:59");
			}
			MessageObd queryObd = new MessageObd();
			queryObd.setCarId(catId);
			queryObd.setStartTime(start);
			queryObd.setEndTime(end);
			queryObd.setType(MessageObd.CONTENT_MONITOR_OIL_RESIDUES);
			List<MessageObd> list = odbDao.getObdByCondition(queryObd);
			//获取昨日油耗
			//获取carId集合
			List<Long> carIdList = list.stream().map( i -> i.getCarId()).distinct().collect(Collectors.toList());
			//设置集合，便于批量插入数据
			List<OcrOilConsumption> OcrOilConsumptionList = new ArrayList<OcrOilConsumption>();
			for(Long carNum:carIdList){
				OcrOilConsumption oilConsumption = new OcrOilConsumption();
				//创建时间为今日时间，记录时间为昨日时间
				oilConsumption.setCarId(carNum);
				oilConsumption.setRecordDate(start);
				oilConsumption.setCreateDate(new Date());
				// && param.getCarId()==carNum
				List<MessageObd> obds = list.stream().filter(param -> param!=null && carNum==param.getCarId().longValue()).collect(Collectors.toList());
				//开始及结束，取3个相近值的平均数
				double startOil;
				double endOil;
				if(obds.size()<=7){
					startOil = obds.get(0).getValue();
					endOil = obds.get(obds.size()-1).getValue();
				}else{
				    startOil = (obds.get(0).getValue()+obds.get(1).getValue()+obds.get(2).getValue())/3;
				    endOil = (obds.get(obds.size()-1).getValue()+obds.get(obds.size()-2).getValue()+obds.get(obds.size()-3).getValue())/3;
				}
				double oilSumForCarid = startOil - endOil;
				//存在索引越界，注意排除
				for(int i=0;i<obds.size()-1;i++){
					//大于10，进行判断是否加油
					if(obds.get(i+1).getValue()-obds.get(i).getValue()>OIL_Consumption_Differ){
						//查询数据库，此条数据对应的速度是否为0  (没有排除在山上停车的情况)
					/*	MessageObd querySpeed = new MessageObd();
						querySpeed.setCarId(carNum);
						querySpeed.setSequenceNumber(obds.get(i+1).getSequenceNumber());
						querySpeed.setType(MessageObd.CONTENT_MONITOR_CAR_SPEED);
						List<MessageObd> tempList=odbDao.getObdByCondition(querySpeed);
						if(CollectionUtils.isNotEmpty(tempList)&&tempList.get(0)!=null&&tempList.get(0).getValue()==0){
							//速度为0，说明加油了，将加油量加上
							 * 
*/							
						if (i+1<obds.size()&&i>0&&(obds.get(i+2).getValue()-obds.get(i-1).getValue()>OIL_Consumption_Differ)) {
							oilSumForCarid=oilSumForCarid+obds.get(i+1).getValue()-obds.get(i).getValue();
						}
						//}
					}
				}
				oilConsumption.setOilConsumptionDay(oilSumForCarid);
				OcrOilConsumptionList.add(oilConsumption);
			}
			//如果carId为null,则属于定时任务，全部插入到数据库中;如果carId不为null,则获取本次订单的耗油量直接返回
			if(null == catId){
				if(OcrOilConsumptionList.size()>0){
					int count=ocrOilConsumptionDao.insertList(OcrOilConsumptionList);
					logger.info("将耗油量汇总插入ocr_oil_consumption成功,数据量为："+count);
				}else{
					logger.info("本次定时任务，无数据插入到ocr_oil_consumption");
				}
			}else{
				logger.info("获取本次行程耗油量,数据量为："+OcrOilConsumptionList.get(0).getOilConsumptionDay());
				return OcrOilConsumptionList.get(0).getOilConsumptionDay();
			}
			
			return null;
		}
	    
	   /* public static void main(String[] args) {
	    	MessageObd mess1 = new MessageObd();
	    	MessageObd mess2 = new MessageObd();
	    	MessageObd mess3 = new MessageObd();
	    	mess1.setCarId(110L);
	    	mess2.setCarId(110L);
	    	mess3.setCarId(110L);
	    	mess1.setRemarks("1");
	    	mess2.setRemarks("2");
	    	mess3.setRemarks("3");
	    	List<MessageObd> list = new ArrayList<MessageObd>();
	    	list.add(mess1);
	    	list.add(mess2);
	    	list.add(mess3);
	    	List<Long> carIdList = list.stream().map( i -> i.getCarId()).distinct().collect(Collectors.toList());
	    	for(Long carNum:carIdList){
	    		List<MessageObd> obds = list.stream().filter(x -> x.getCarId()==carNum).collect(Collectors.toList());
	    		System.out.println(obds.size());
	    	}
	    	
		}*/
	    
		/**
		 * 查询月油耗
		 * @param id
		 * @param year
		 * @return
		 */
		public List<OilMileageQueryVO> monthOilConsumptionList (String id,String year){
			
			return ocrOilConsumptionDao.monthOilConsumptionList(id, year);
		}
		
		/**
		 * 
		 * @param catId
		 * @param start
		 * @param end
		 */
	    @Transactional(readOnly = false)
		public Double getOilConsumptionTest(Long catId, Date start,
				Date end,String date) {
			
			if(null==start && null == end){
				start = DateUtils.parseDate(date + " 00:00:00");
				end = DateUtils.parseDate(date + " 23:59:59");
			}
			MessageObd queryObd = new MessageObd();
			queryObd.setCarId(catId);
			queryObd.setStartTime(start);
			queryObd.setEndTime(end);
			queryObd.setType(MessageObd.CONTENT_MONITOR_OIL_RESIDUES);
			List<MessageObd> list = odbDao.getObdByCondition(queryObd);
			//获取昨日油耗
			//获取carId集合
			List<Long> carIdList = list.stream().map( i -> i.getCarId()).distinct().collect(Collectors.toList());
			//设置集合，便于批量插入数据
			List<OcrOilConsumption> OcrOilConsumptionList = new ArrayList<OcrOilConsumption>();
			for(Long carNum:carIdList){
				OcrOilConsumption oilConsumption = new OcrOilConsumption();
				//创建时间为今日时间，记录时间为昨日时间
				oilConsumption.setCarId(carNum);
				oilConsumption.setRecordDate(start);
				oilConsumption.setCreateDate(new Date());
				// && param.getCarId()==carNum
				List<MessageObd> obds = list.stream().filter(param -> param!=null && carNum==param.getCarId().longValue()).collect(Collectors.toList());
				//开始及结束，取3个相近值的平均数
				double startOil;
				double endOil;
				if(obds.size()<=7){
					startOil = obds.get(0).getValue();
					endOil = obds.get(obds.size()-1).getValue();
				}else{
				    startOil = (obds.get(0).getValue()+obds.get(1).getValue()+obds.get(2).getValue())/3;
				    endOil = (obds.get(obds.size()-1).getValue()+obds.get(obds.size()-2).getValue()+obds.get(obds.size()-3).getValue())/3;
				}
				double oilSumForCarid = startOil - endOil;
				//存在索引越界，注意排除
				for(int i=0;i<obds.size()-1;i++){
					//大于10，进行判断是否加油
					if(obds.get(i+1).getValue()-obds.get(i).getValue()>OIL_Consumption_Differ){
						if (i+1<obds.size()&&i>0&&(obds.get(i+2).getValue()-obds.get(i-1).getValue()>OIL_Consumption_Differ)) {
							oilSumForCarid=oilSumForCarid+obds.get(i+1).getValue()-obds.get(i).getValue();
						}
						//查询数据库，此条数据对应的速度是否为0  (没有排除在山上停车的情况)
					/*	MessageObd querySpeed = new MessageObd();
						querySpeed.setCarId(carNum);
						querySpeed.setSequenceNumber(obds.get(i+1).getSequenceNumber());
						querySpeed.setType(MessageObd.CONTENT_MONITOR_CAR_SPEED);
						List<MessageObd> tempList=odbDao.getObdByCondition(querySpeed);
						if(CollectionUtils.isNotEmpty(tempList)&&tempList.get(0)!=null&&tempList.get(0).getValue()==0){
							//速度为0，说明加油了，将加油量加上
					 */						
						//}
					}
				}
				oilConsumption.setOilConsumptionDay(oilSumForCarid);
				OcrOilConsumptionList.add(oilConsumption);
			}
			//如果carId为null,则属于定时任务，全部插入到数据库中;如果carId不为null,则获取本次订单的耗油量直接返回
			if(null == catId){
				if(OcrOilConsumptionList.size()>0){
					//int count=ocrOilConsumptionDao.insertList(OcrOilConsumptionList);
					logger.info("将耗油量汇总插入ocr_oil_consumption成功,数据量为：");
				}else{
					logger.info("本次定时任务，无数据插入到ocr_oil_consumption");
				}
			}else{
				logger.info("获取本次行程耗油量,数据量为："+OcrOilConsumptionList.get(0).getOilConsumptionDay());
				return OcrOilConsumptionList.get(0).getOilConsumptionDay();
			}
			
			return null;
		}
		
	    
}