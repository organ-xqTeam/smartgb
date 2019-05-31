/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.garbage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.garbage.dao.GarbageBinCleanDao;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinClean;

/**
 * 垃圾清理Service
 * @version 2018-07-03
 */
@Service
@Transactional(readOnly = true)
public class GarbageBinCleanService extends CrudService<GarbageBinCleanDao, GarbageBinClean> {
	
	public GarbageBinClean get(String id) {
		return super.get(id);
	}
	
	
	public List<GarbageBinClean> findList(GarbageBinClean gbClean) {
		return super.findList(gbClean);
	}
	
	public Page<GarbageBinClean> findPage(Page<GarbageBinClean> page, GarbageBinClean gbClean) {
		return super.findPage(page, gbClean);
	}

	
	@Transactional(readOnly = false)
	public void save(GarbageBinClean gbClean) {
		super.save(gbClean);
	}
	
	@Transactional(readOnly = false)
	public void delete(GarbageBinClean gbClean) {
		super.delete(gbClean);
	}
	
}