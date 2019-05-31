/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.message.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.JPushDriverUtil;
import com.thinkgem.jeesite.common.utils.JPushUserUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.message.entity.OcrMessage;
import com.thinkgem.jeesite.modules.message.service.OcrMessageService;

/**
 * 推送管理Controller
 * @author zr
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/message/ocrMessage")
public class OcrMessageController extends BaseController {

	@Autowired
	private OcrMessageService ocrMessageService;
	
	@ModelAttribute
	public OcrMessage get(@RequestParam(required=false) String id) {
		OcrMessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrMessageService.get(id);
		}
		if (entity == null){
			entity = new OcrMessage();
		}
		return entity;
	}
	
	@RequiresPermissions("message:ocrMessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrMessage ocrMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrMessage> page = ocrMessageService.findPage(new Page<OcrMessage>(request, response), ocrMessage); 
		model.addAttribute("page", page);
		return "modules/message/ocrMessageList";
	}

	@RequiresPermissions("message:ocrMessage:view")
	@RequestMapping(value = "form")
	public String form(OcrMessage ocrMessage, Model model) {
		model.addAttribute("ocrMessage", ocrMessage);
		return "modules/message/ocrMessageForm";
	}
	@RequiresPermissions("message:ocrMessage:view")
	@RequestMapping(value = "check")
	public String check(OcrMessage ocrMessage, Model model) {
		model.addAttribute("ocrMessage", ocrMessage);
		return "modules/message/ocrMessageCheck";
	}

	@RequiresPermissions("message:ocrMessage:edit")
	@RequestMapping(value = "save")
	public String save(OcrMessage ocrMessage, Model model, RedirectAttributes redirectAttributes) {
		if (ocrMessage.getIsNewRecord()) {
			ocrMessage.setStatus("0");
			ocrMessage.setReceiveSide("1");
		}
		if (!beanValidator(model, ocrMessage)){
			return form(ocrMessage, model);
		}
		ocrMessageService.save(ocrMessage);
		addMessage(redirectAttributes, "保存推送信息成功");
		return "redirect:"+Global.getAdminPath()+"/message/ocrMessage/?repage";
	}
	
	@RequiresPermissions("message:ocrMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrMessage ocrMessage, RedirectAttributes redirectAttributes) {
		ocrMessageService.delete(ocrMessage);
		addMessage(redirectAttributes, "删除推送信息成功");
		return "redirect:"+Global.getAdminPath()+"/message/ocrMessage/?repage";
	}
	
	@RequiresPermissions("message:ocrMessage:edit")
	@RequestMapping(value = "send")
	public String send(OcrMessage ocrMessage, RedirectAttributes redirectAttributes) {
		
		/*if ("0".equals(ocrMessage.getReceiveSide())) {
			JPushUserUtil.sentMessageAll(ocrMessage.getContent(),ocrMessage.getTitle(), "", "100", "");
		} else if ("1".equals(ocrMessage.getReceiveSide())) {
			JPushDriverUtil.sentMessageAll(ocrMessage.getContent(),ocrMessage.getTitle(), "", "100", "");

		}*/
		JPushDriverUtil.sentMessageAll(ocrMessage.getContent(),ocrMessage.getTitle(), "", "100", "");
		ocrMessage.setStatus("1");
		ocrMessageService.save(ocrMessage);
		addMessage(redirectAttributes, "推送信息成功");
		return "redirect:"+Global.getAdminPath()+"/message/ocrMessage/?repage";
	}

}