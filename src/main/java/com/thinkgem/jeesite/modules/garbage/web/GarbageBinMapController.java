package com.thinkgem.jeesite.modules.garbage.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinInfo;
import com.thinkgem.jeesite.modules.garbage.service.GarbageBinInfoService;

/**
 * 垃圾桶地图Controller
 * @author ybc
 * @version 2018-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/garbagebin/gbMap")
public class GarbageBinMapController extends BaseController {

	@Autowired
	private GarbageBinInfoService gbInfoService;
	
	@RequiresPermissions("gb:map:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		ObjectMapper mapper= new ObjectMapper();
		List<GarbageBinInfo> allGbInfo = gbInfoService.findListAll();
		model.addAttribute("allGbInfo", mapper.writeValueAsString(allGbInfo));
		return "modules/garbage/garbageMap";
	}
}