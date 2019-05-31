/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.document.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.document.entity.OcrDocument;

/**
 * 文档设置DAO接口
 * @author gaoly
 * @version 2017-08-15
 */
@MyBatisDao
public interface OcrDocumentDao extends CrudDao<OcrDocument> {
	
}