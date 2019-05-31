package com.thinkgem.jeesite.common.timedTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.obd.service.OcrOilConsumptionService;

@Service
@Lazy(false)
public class TimedOilCalculate {

	@Autowired
	private OcrOilConsumptionService ocrOilService;

	/*@Scheduled(cron = "0 0/1 * * * ?")*/
	/*@Scheduled(cron = "0 0 6 * * ?")*/
	public void oilConsumptionTask() {
		//System.out.println("进入定时计算油耗任务");
		ocrOilService.getOilConsumption(null, null, null);
	}

}
