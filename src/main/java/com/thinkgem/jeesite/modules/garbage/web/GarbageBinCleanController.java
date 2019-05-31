package com.thinkgem.jeesite.modules.garbage.web;

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
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinClean;
import com.thinkgem.jeesite.modules.garbage.service.GarbageBinCleanService;

/**
 * 垃圾清理记录Controller
 * @author ybc
 * @version 2018-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/garbagebin/cleaninfo")
public class GarbageBinCleanController extends BaseController {

	@Autowired
	private GarbageBinCleanService gbCleanService;
	
	@RequiresPermissions("gb:cleaninfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(GarbageBinClean gbClean, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GarbageBinClean> page = gbCleanService.findPage(new Page<GarbageBinClean>(request, response), gbClean); 
		model.addAttribute("page", page);
		return "modules/garbage/cleaninfoList";
	}

	@RequiresPermissions("gb:cleaninfo:edit")
	@RequestMapping(value = "delete")
	public String delete(GarbageBinClean gbClean, RedirectAttributes redirectAttributes) {
		gbCleanService.delete(gbClean);
		addMessage(redirectAttributes, "删除记录成功");
		return "redirect:"+Global.getAdminPath()+"/garbagebin/cleaninfo/?repage";
	}
}