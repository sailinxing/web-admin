package com.lixinxin.service;

import java.sql.SQLException;
import java.util.List;

import com.lixinxin.dao.AdminShowCategoryDao;
import com.lixinxin.domain.Category;

public class AdminShowCategoryService {

	public List<Category> findAllCategorys() throws SQLException {
		AdminShowCategoryDao dao=new AdminShowCategoryDao();
		List<Category> categoryList=dao.findAllCategorys();
		return categoryList;
	}

}
