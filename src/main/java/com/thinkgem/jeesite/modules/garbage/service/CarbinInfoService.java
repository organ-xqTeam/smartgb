package com.thinkgem.jeesite.modules.garbage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.garbage.dao.CarbinInfoDao;
import com.thinkgem.jeesite.modules.garbage.entity.CarbinInfo;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinClean;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinInfo;

@Service
@Transactional(readOnly = true)
public class CarbinInfoService extends CrudService<CarbinInfoDao, CarbinInfo> {

	public List<CarbinInfo> findListAll() {
		return this.findList(null);
	}
	
	public List<CarbinInfo> findList(CarbinInfo gbInfo) {
		return super.findList(gbInfo);
	}
	
}
