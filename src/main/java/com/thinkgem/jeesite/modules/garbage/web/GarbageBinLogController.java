package com.thinkgem.jeesite.modules.garbage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinLog;
import com.thinkgem.jeesite.modules.garbage.service.GarbageBinLogService;

/**
 * DTO通讯日志Controller
 * @author ybc
 * @version 2018-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/garbagebin/dtolog")
public class GarbageBinLogController extends BaseController {

	@Autowired
	private GarbageBinLogService gbLogService;
	
	@RequiresPermissions("gb:dtolog:view")
	@RequestMapping(value = {"list", ""})
	public String list(GarbageBinLog gbLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GarbageBinLog> page = gbLogService.findPage(new Page<GarbageBinLog>(request, response), gbLog); 
		model.addAttribute("page", page);
		return "modules/garbage/dtoLog";
	}

}