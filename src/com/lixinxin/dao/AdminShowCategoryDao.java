package com.lixinxin.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lixinxin.Utils.C3P0Utils;
import com.lixinxin.domain.Category;

public class AdminShowCategoryDao {

	public List<Category> findAllCategorys() throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from category";
		List<Category> categoryList= qr.query(sql, new BeanListHandler<Category>(Category.class));
		return categoryList;
	}

}
