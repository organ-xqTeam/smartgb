package com.thinkgem.jeesite.common.timedTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.order.entity.OcrOrder;
import com.thinkgem.jeesite.modules.order.service.OcrOrderService;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;
import com.thinkgem.jeesite.modules.task.entity.OcrTaskOrderVO;
import com.thinkgem.jeesite.modules.task.service.OcrTaskService;

@Service
@Lazy(false)
public class TimedReject {
	@Autowired
	private OcrTaskService ocrTaskService;
	@Autowired
	private OcrOrderService ocrOrderService;
	//间隔时常
	/*@Scheduled(cron = "0 0/1 * * * ?")*/
	public void timingTask(){
		
		updateTimeoutOrders();
		
	}
	/**
	 * 司机超时未接单，自动拒绝
	 */
	private void updateTimeoutOrders(){
		OcrTask ocrTaskQuit = new OcrTask();
		ocrTaskQuit.setDelFlag("0");
		ocrTaskQuit.setStatus("1");
		List<OcrTaskOrderVO> list = ocrTaskService.findListAndOrderStatus(ocrTaskQuit);
		List<OcrTask> updateTaskList = new ArrayList<OcrTask>();
		List<OcrOrder> updateOrderList = new ArrayList<OcrOrder>();

		for (OcrTaskOrderVO ocrTaskOrderVO : list) {
			long  timeDifference = new Date().getTime() - ocrTaskOrderVO.getUpdateDate().getTime();
			//超时时常
			if (timeDifference > 1000 * 60 * 10) {
				if (null != ocrTaskOrderVO.getOrderStatus()) {
					OcrOrder ocrOrder = new OcrOrder();
					OcrTask ocrTask = new OcrTask();
					ocrOrder.setId(ocrTaskOrderVO.getOrderId());
					ocrTask.setId(ocrTaskOrderVO.getId());
					updateTaskList.add(ocrTask);
					updateOrderList.add(ocrOrder);					
				}
			}
		}
		

		if (updateTaskList.size() > 0 && updateOrderList.size()>0) {
			ocrTaskService.batchUpdateStatus(updateTaskList);
			ocrOrderService.batchUpdateStatus(updateOrderList);
			
		}
		
	}
}
