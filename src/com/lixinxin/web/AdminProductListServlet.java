package com.lixinxin.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lixinxin.domain.Category;
import com.lixinxin.domain.Product;
import com.lixinxin.service.AdminShowCategoryService;
import com.lixinxin.service.ShowProductsService;

public class AdminProductListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShowProductsService service = new ShowProductsService();
		List<Product> productList = service.findAllProducts();
		request.setAttribute("productList", productList);
		AdminShowCategoryService service1 = new AdminShowCategoryService();
		List<Category> categoryList = null;
		try {
			categoryList = service1.findAllCategorys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/admin/showProducts.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}