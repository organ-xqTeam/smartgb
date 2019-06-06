/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.garbage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.garbage.dao.GarbageBinInfoDao;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinInfo;

/**
 * 垃圾桶管理Service
 * @author ybc
 * @version 2018-06-10
 */
@Service
@Transactional(readOnly = true)
public class GarbageBinInfoService extends CrudService<GarbageBinInfoDao, GarbageBinInfo> {
	
	public GarbageBinInfo get(String id) {
		return super.get(id);
	}
	
	public List<GarbageBinInfo> getInfosByIds(String ids) {
		return super.findListByIds(ids);
	}
	
	public List<GarbageBinInfo> findListAll() {
		return this.findList(null);
	}
	
	public List<GarbageBinInfo> findList(GarbageBinInfo gbInfo) {
		return super.findList(gbInfo);
	}
	
	public Page<GarbageBinInfo> findPage(Page<GarbageBinInfo> page, GarbageBinInfo gbInfo) {
		return super.findPage(page, gbInfo);
	}

	
	@Transactional(readOnly = false)
	public void save(GarbageBinInfo gbClean) {
		super.save(gbClean);
	}
	
	@Transactional(readOnly = false)
	public void delete(GarbageBinInfo gbInfo) {
		super.delete(gbInfo);
	}
	
}