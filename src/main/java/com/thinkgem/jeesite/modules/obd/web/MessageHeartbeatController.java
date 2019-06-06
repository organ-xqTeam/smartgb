/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.web;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle;
import com.thinkgem.jeesite.modules.car.service.OcrVehicleService;
import com.thinkgem.jeesite.modules.obd.entity.MessageHeartbeat;
import com.thinkgem.jeesite.modules.obd.service.MessageHeartbeatService;

/**
 * 心跳Controller
 * @author zr
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/obd/heartbeat/messageHeartbeat")
public class MessageHeartbeatController extends BaseController {

	@Autowired
	private MessageHeartbeatService messageHeartbeatService;
	
	@Autowired
	private OcrVehicleService OcrVehicleService;
	
	@ModelAttribute
	public MessageHeartbeat get(@RequestParam(required=false) String id) {
		MessageHeartbeat entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = messageHeartbeatService.get(id);
		}
		if (entity == null){
			entity = new MessageHeartbeat();
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "findMessageHeartbeat")
	public Map<String, Object> findMessageHeartbeat(String equipmentImei,String id){
		Map<String, Object> returnMap = Maps.newHashMap();
		OcrVehicle ocrVehicle = new OcrVehicle();
		ocrVehicle.setEquipmentImei(equipmentImei);
		List<OcrVehicle> ocrVehicleList= OcrVehicleService.findList(ocrVehicle);
		if (ocrVehicleList!=null && ocrVehicleList.size()>0) {
			if (!id.equals(ocrVehicleList.get(0).getId())) {
				returnMap.put("status", "1");//该imei号已绑定其他车辆
				return returnMap;				
			}
		}
		MessageHeartbeat messageHeartbeat = new MessageHeartbeat();
		Long equipmentImeiL = Long.valueOf(equipmentImei);
		messageHeartbeat.setCarId(equipmentImeiL);
		
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.add(Calendar.MINUTE, -5);// 5分钟之前的时间
		Date beforeD = beforeTime.getTime();
		messageHeartbeat.setCreateTime(beforeD);
		
		List<MessageHeartbeat> messageHeartbeatList= messageHeartbeatService.findList(messageHeartbeat);
		if (messageHeartbeatList!=null && messageHeartbeatList.size()>0) {
			returnMap.put("status", "0");//有5分钟内的心跳记录
		}else {
			returnMap.put("status", "2");//没有5分钟内的心跳记录

		}
		
		
		return returnMap;
	}
	
	@RequiresPermissions("obd:heartbeat:messageHeartbeat:view")
	@RequestMapping(value = {"list", ""})
	public String list(MessageHeartbeat messageHeartbeat, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MessageHeartbeat> page = messageHeartbeatService.findPage(new Page<MessageHeartbeat>(request, response), messageHeartbeat); 
		model.addAttribute("page", page);
		return "modules/obd/heartbeat/messageHeartbeatList";
	}

	@RequiresPermissions("obd:heartbeat:messageHeartbeat:view")
	@RequestMapping(value = "form")
	public String form(MessageHeartbeat messageHeartbeat, Model model) {
		model.addAttribute("messageHeartbeat", messageHeartbeat);
		return "modules/obd/heartbeat/messageHeartbeatForm";
	}

	@RequiresPermissions("obd:heartbeat:messageHeartbeat:edit")
	@RequestMapping(value = "save")
	public String save(MessageHeartbeat messageHeartbeat, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, messageHeartbeat)){
			return form(messageHeartbeat, model);
		}
		messageHeartbeatService.save(messageHeartbeat);
		addMessage(redirectAttributes, "保存心跳成功");
		return "redirect:"+Global.getAdminPath()+"/obd/heartbeat/messageHeartbeat/?repage";
	}
	
	@RequiresPermissions("obd:heartbeat:messageHeartbeat:edit")
	@RequestMapping(value = "delete")
	public String delete(MessageHeartbeat messageHeartbeat, RedirectAttributes redirectAttributes) {
		messageHeartbeatService.delete(messageHeartbeat);
		addMessage(redirectAttributes, "删除心跳成功");
		return "redirect:"+Global.getAdminPath()+"/obd/heartbeat/messageHeartbeat/?repage";
	}

}