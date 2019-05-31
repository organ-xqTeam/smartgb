/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ocruser.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ocruser.entity.OcrFeedback;
import com.thinkgem.jeesite.modules.ocruser.service.OcrFeedbackService;

/**
 * 用户反馈Controller
 * @author zr
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ocruser/ocrFeedback")
public class OcrFeedbackController extends BaseController {

	@Autowired
	private OcrFeedbackService ocrFeedbackService;
	
	@ModelAttribute
	public OcrFeedback get(@RequestParam(required=false) String id) {
		OcrFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrFeedbackService.get(id);
		}
		if (entity == null){
			entity = new OcrFeedback();
		}
		return entity;
	}
	
	@RequiresPermissions("ocruser:ocrFeedback:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrFeedback ocrFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrFeedback> page = ocrFeedbackService.findPage(new Page<OcrFeedback>(request, response), ocrFeedback); 
		model.addAttribute("page", page);
		return "modules/ocruser/ocrFeedbackList";
	}

	@RequiresPermissions("ocruser:ocrFeedback:view")
	@RequestMapping(value = "form")
	public String form(OcrFeedback ocrFeedback, Model model) {
		if (StringUtils.isNotBlank(ocrFeedback.getId())&&ocrFeedback.getStatus().equals("0")) {
			ocrFeedback.setStatus("1");
			ocrFeedbackService.save(ocrFeedback);
		}
		model.addAttribute("ocrFeedback", ocrFeedback);
		return "modules/ocruser/ocrFeedbackForm";
	}

	@RequiresPermissions("ocruser:ocrFeedback:edit")
	@RequestMapping(value = "save")
	public String save(OcrFeedback ocrFeedback, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrFeedback)){
			return form(ocrFeedback, model);
		}
		ocrFeedbackService.save(ocrFeedback);
		addMessage(redirectAttributes, "保存用户反馈成功");
		return "redirect:"+Global.getAdminPath()+"/ocruser/ocrFeedback/?repage";
	}
	
	@RequiresPermissions("ocruser:ocrFeedback:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrFeedback ocrFeedback, RedirectAttributes redirectAttributes) {
		ocrFeedbackService.delete(ocrFeedback);
		addMessage(redirectAttributes, "删除用户反馈成功");
		return "redirect:"+Global.getAdminPath()+"/ocruser/ocrFeedback/?repage";
	}

}