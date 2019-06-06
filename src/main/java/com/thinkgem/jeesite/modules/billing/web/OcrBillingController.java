/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.billing.web;

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
import com.thinkgem.jeesite.modules.billing.entity.OcrBilling;
import com.thinkgem.jeesite.modules.billing.service.OcrBillingService;

/**
 * 计费规则Controller
 * @author zr
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/billing/ocrBilling")
public class OcrBillingController extends BaseController {

	@Autowired
	private OcrBillingService ocrBillingService;
	
	@ModelAttribute
	public OcrBilling get(@RequestParam(required=false) String id) {
		OcrBilling entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrBillingService.get(id);
		}
		if (entity == null){
			entity = new OcrBilling();
		}
		return entity;
	}
	
	@RequiresPermissions("billing:ocrBilling:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrBilling ocrBilling, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrBilling> page = ocrBillingService.findPage(new Page<OcrBilling>(request, response), ocrBilling); 
		model.addAttribute("page", page);
		return "modules/billing/ocrBillingList";
	}

	@RequiresPermissions("billing:ocrBilling:view")
	@RequestMapping(value = "form")
	public String form(OcrBilling ocrBilling, Model model) {
		ocrBilling = ocrBillingService.selectOcrBillingNew();
		model.addAttribute("ocrBilling", ocrBilling);
		return "modules/billing/ocrBillingForm";
	}

	@RequiresPermissions("billing:ocrBilling:edit")
	@RequestMapping(value = "save")
	public String save(OcrBilling ocrBilling, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrBilling)){
			return form(ocrBilling, model);
		}
		ocrBillingService.save(ocrBilling);
		addMessage(redirectAttributes, "保存计费规则成功");
		return "redirect:"+Global.getAdminPath()+"/billing/ocrBilling/form?repage";
	}
	
	@RequiresPermissions("billing:ocrBilling:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrBilling ocrBilling, RedirectAttributes redirectAttributes) {
		ocrBillingService.delete(ocrBilling);
		addMessage(redirectAttributes, "删除计费规则成功");
		return "redirect:"+Global.getAdminPath()+"/billing/ocrBilling/?repage";
	}

}