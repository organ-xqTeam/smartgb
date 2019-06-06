/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.obd.dao.MessageHeartbeatDao;
import com.thinkgem.jeesite.modules.obd.entity.MessageHeartbeat;

/**
 * 心跳Service
 * @author zr
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class MessageHeartbeatService extends CrudService<MessageHeartbeatDao, MessageHeartbeat> {

	public MessageHeartbeat get(String id) {
		return super.get(id);
	}
	
	public List<MessageHeartbeat> findList(MessageHeartbeat messageHeartbeat) {
		return super.findList(messageHeartbeat);
	}
	
	public Page<MessageHeartbeat> findPage(Page<MessageHeartbeat> page, MessageHeartbeat messageHeartbeat) {
		return super.findPage(page, messageHeartbeat);
	}
	
	@Transactional(readOnly = false)
	public void save(MessageHeartbeat messageHeartbeat) {
		super.save(messageHeartbeat);
	}
	
	@Transactional(readOnly = false)
	public void delete(MessageHeartbeat messageHeartbeat) {
		super.delete(messageHeartbeat);
	}
	
}