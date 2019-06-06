package com.thinkgem.jeesite.modules.garbage.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriver;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinInfo;
import com.thinkgem.jeesite.modules.garbage.service.GarbageBinInfoService;

/**
 * 垃圾桶管理Controller
 * @author ybc
 * @version 2018-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/garbagebin/checkinfo")
public class GarbageBinCheckController extends BaseController {

	@Autowired
	private GarbageBinInfoService gbInfoService;
	
	@RequiresPermissions("gb:checkinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(GarbageBinInfo gbInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GarbageBinInfo> page = gbInfoService.findPage(new Page<GarbageBinInfo>(request, response), gbInfo); 
		model.addAttribute("page", page);
		return "modules/garbage/checkinfoList";
	}

	@RequiresPermissions("gb:checkinfo:view")
	@RequestMapping(value = "form")
	public String form(OcrDriver ocrDriver, Model model) {
		model.addAttribute("ocrDriver", ocrDriver);
		return "modules/garbage/checkinfoForm";
	}

	@RequestMapping(value = "listByIds")
	@ResponseBody
	public List<GarbageBinInfo> listByIds(@RequestBody String dtuIds) {
		String ids = (dtuIds.substring(1,dtuIds.length())).substring(0,dtuIds.length()-2);
		List<GarbageBinInfo> infos = gbInfoService.findListByIds(ids);
		return infos;
	}
	
}