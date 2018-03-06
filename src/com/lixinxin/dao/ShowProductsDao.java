package com.lixinxin.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lixinxin.Utils.C3P0Utils;
import com.lixinxin.domain.Product;
import com.lixinxin.domain.vo.Condition;




public class ShowProductsDao {

	public List<Product> findAllProducts() throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product";
		List<Product> productList = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return productList;
	}

	public void deleteProduct(String pid) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from product where pid=?";
		qr.update(sql,pid);
		
	}

	public void addProduct(Product p) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),
				p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),p.getPflag(),p.getCid());
	}

	public Product findProductById(String pid) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where pid=?";
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

	public void editProduct(Product p) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,"
				+ "pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
		qr.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),
				p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),p.getPflag(),p.getCid(),p.getPid());
	}

	public List<Product> searchProducts(Condition condition) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where 1=1";
		ArrayList<String> params=new ArrayList<>();
		if(condition.getPname()!=null&&!"".equals(condition.getPname().trim())){
			sql+=" and pname like ?";
			params.add("%"+condition.getPname().trim()+"%");
		}
		if(condition.getIs_hot()!=null&&!"".equals(condition.getIs_hot().trim())){
			sql+=" and is_hot=?";
			params.add(condition.getIs_hot().trim());
		}
		if(condition.getCid()!=null&&!"".equals(condition.getCid().trim())){
			sql+=" and cid=?";
			params.add(condition.getCid().trim());
		}
		List<Product> productList = qr.query(sql,new BeanListHandler<Product>(Product.class), params.toArray());
		return productList;
	}

	public List<Product> findProductByWord(String word) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where pname like ? limit ?,?";
		List<Product> listProduct=qr.query(sql,new BeanListHandler<Product>(Product.class),word+"%",0,5);
		return listProduct;
	}

}
