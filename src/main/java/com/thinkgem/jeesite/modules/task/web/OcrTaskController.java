/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.web;

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
import com.thinkgem.jeesite.modules.task.entity.OcrTask;
import com.thinkgem.jeesite.modules.task.service.OcrTaskService;

/**
 * 任务管理Controller
 * @author gaoly
 * @version 2017-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/task/ocrTask")
public class OcrTaskController extends BaseController {

	@Autowired
	private OcrTaskService ocrTaskService;
	
	@ModelAttribute
	public OcrTask get(@RequestParam(required=false) String id) {
		OcrTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrTaskService.get(id);
		}
		if (entity == null){
			entity = new OcrTask();
		}
		return entity;
	}
	
	@RequiresPermissions("task:ocrTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrTask ocrTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrTask> page = ocrTaskService.findPage(new Page<OcrTask>(request, response), ocrTask); 
		model.addAttribute("page", page);
		return "modules/task/ocrTaskList";
	}

	@RequiresPermissions("task:ocrTask:view")
	@RequestMapping(value = "form")
	public String form(OcrTask ocrTask, Model model) {
		model.addAttribute("ocrTask", ocrTask);
		return "modules/task/ocrTaskForm";
	}

	@RequiresPermissions("task:ocrTask:edit")
	@RequestMapping(value = "save")
	public String save(OcrTask ocrTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrTask)){
			return form(ocrTask, model);
		}
		ocrTaskService.save(ocrTask);
		addMessage(redirectAttributes, "保存任务管理成功");
		return "redirect:"+Global.getAdminPath()+"/task/ocrTask/?repage";
	}
	
	@RequiresPermissions("task:ocrTask:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrTask ocrTask, RedirectAttributes redirectAttributes) {
		ocrTaskService.delete(ocrTask);
		addMessage(redirectAttributes, "删除任务管理成功");
		return "redirect:"+Global.getAdminPath()+"/task/ocrTask/?repage";
	}

}