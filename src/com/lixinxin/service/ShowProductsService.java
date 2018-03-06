package com.lixinxin.service;

import java.sql.SQLException;
import java.util.List;

import com.lixinxin.dao.ShowProductsDao;
import com.lixinxin.domain.Product;
import com.lixinxin.domain.vo.Condition;


public class ShowProductsService {

	public List<Product> findAllProducts() {
		ShowProductsDao dao=new ShowProductsDao();
		List<Product> productList=null;
		try {
			productList = dao.findAllProducts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public void deleteProduct(String pid) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		dao.deleteProduct(pid);		
	}

	public void addProduct(Product product) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		dao.addProduct(product);		
	}

	public Product findProductById(String pid) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		Product product=dao.findProductById(pid);
		return product;
	}

	public void editProduct(Product product) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		dao.editProduct(product);
	}

	public List<Product> searchProducts(Condition condition) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		List<Product> productList=dao.searchProducts(condition);
		return productList;
	}

	public List<Product> findProductByWord(String word) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		List<Product> listProduct=dao.findProductByWord(word);
		return listProduct;
	}

}
