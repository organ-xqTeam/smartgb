/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.garbage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.garbage.dao.GarbageBinLogDao;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinLog;

/**
 * DTO日志Service
 * @version 2018-08-11
 */
@Service
@Transactional(readOnly = true)
public class GarbageBinLogService extends CrudService<GarbageBinLogDao, GarbageBinLog> {
	
}