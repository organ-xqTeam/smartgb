/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ocruser.web;

import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ocruser.entity.OcrUser;
import com.thinkgem.jeesite.modules.ocruser.service.OcrUserService;

/**
 * 租车用户Controller
 * @author zr
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/ocruser/ocrUser")
public class OcrUserController extends BaseController {

	@Autowired
	private OcrUserService ocrUserService;
	
	@ModelAttribute
	public OcrUser get(@RequestParam(required=false) String id) {
		OcrUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrUserService.get(id);
		}
		if (entity == null){
			entity = new OcrUser();
		}
		return entity;
	}
	
	@RequestMapping(value = {"dataStatistics"})
	public String dataStatistics( HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> map = ocrUserService.dataStatistics();
		model.addAttribute("statisticsDate", new Date());
		model.addAttribute("map", map);
		//TODO 列表公司字段 by zr
		return "modules/ocruser/dataStatistics";
	}
	
	@RequiresPermissions("ocruser:ocrUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrUser ocrUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrUser> page = ocrUserService.findPage(new Page<OcrUser>(request, response), ocrUser); 
		model.addAttribute("page", page);
		//TODO 列表公司字段 by zr
		return "modules/ocruser/ocrUserList";
	}

	@RequiresPermissions("ocruser:ocrUser:view")
	@RequestMapping(value = "form")
	public String form(OcrUser ocrUser, Model model) {
		model.addAttribute("ocrUser", ocrUser);
		return "modules/ocruser/ocrUserForm";
	}

	@RequiresPermissions("ocruser:ocrUser:edit")
	@RequestMapping(value = "save")
	public String save(OcrUser ocrUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrUser)){
			return form(ocrUser, model);
		}
		ocrUserService.save(ocrUser);
		addMessage(redirectAttributes, "保存租车用户成功");
		return "redirect:"+Global.getAdminPath()+"/ocruser/ocrUser/?repage";
	}
	
	@RequiresPermissions("ocruser:ocrUser:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrUser ocrUser, RedirectAttributes redirectAttributes) {
		ocrUserService.delete(ocrUser);
		addMessage(redirectAttributes, "删除租车用户成功");
		return "redirect:"+Global.getAdminPath()+"/ocruser/ocrUser/?repage";
	}

}