package com.lixinxin.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lixinxin.domain.Product;
import com.lixinxin.service.ShowProductsService;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String word = request.getParameter("word");
		ShowProductsService service=new ShowProductsService();
		List<Product> listProduct=null;
		try {
			listProduct=service.findProductByWord(word);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson=new Gson();
		String json = gson.toJson(listProduct);
		if(json!=null){
		response.getWriter().write(json);
		}
		System.out.println(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}