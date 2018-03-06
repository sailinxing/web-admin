package com.lixinxin.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.lixinxin.domain.Category;
import com.lixinxin.domain.Product;
import com.lixinxin.domain.vo.Condition;
import com.lixinxin.service.AdminShowCategoryService;
import com.lixinxin.service.ShowProductsService;

public class AdminSearchProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> map = request.getParameterMap();
		Condition condition=new Condition();
		try {
			BeanUtils.populate(condition, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		ShowProductsService service=new ShowProductsService();
		List<Product> productList=null;
		try {
			 productList=service.searchProducts(condition);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AdminShowCategoryService service1=new AdminShowCategoryService();
		List<Category> categoryList =null;
		try {
			 categoryList = service1.findAllCategorys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("productList", productList);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("condition", condition);
		request.getRequestDispatcher("/admin/showProducts.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}