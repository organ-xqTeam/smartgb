/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverWork;
import com.thinkgem.jeesite.modules.drive.service.OcrDriverWorkService;

/**
 * 司机管理Controller
 * @author zr
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/drive/work")
public class OcrDriverWorkController extends BaseController {

	@Autowired
	private OcrDriverWorkService ocrDriverWorkService;
	
	@RequiresPermissions("drive:work:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrDriverWork ocrDriver, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrDriverWork> page = ocrDriverWorkService.findPage(new Page<OcrDriverWork>(request, response), ocrDriver); 
		model.addAttribute("page", page);
		return "modules/drive/ocrDriverWorkList";
	}


	
	@RequiresPermissions("drive:work:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrDriverWork ocrDriver, RedirectAttributes redirectAttributes) {
		ocrDriverWorkService.delete(ocrDriver);
		addMessage(redirectAttributes, "删除司机成功");
		return "redirect:"+Global.getAdminPath()+"/drive/work/?repage";
	}

}