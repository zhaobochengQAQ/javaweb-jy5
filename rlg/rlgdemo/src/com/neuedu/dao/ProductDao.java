package com.neuedu.dao;

import com.neuedu.pojo.Product;
import com.neuedu.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;


public class ProductDao {

    //商品列表
    public List<Product> selectAll(String pageSize, String pageNum) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from product ";
        List<Product> li = null;
        try {
            li= qr.query(sql, new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }


    //搜索商品
    public List<Product> search_product(String productName) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from product where pname like ?";
        String mh = "%" + productName + "%";
        List<Product> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Product>(Product.class),mh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }


    //商品详情
    public Product product_detail(String productId) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from product where id = ?";
        Product product = null;
        try {
            product = qr.query(sql,new BeanHandler<Product>(Product.class),productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  product;
    }

    //增加/更新商品
    public List<Product> add_product(String parentId, String productName, String status) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from  product where parentId = ? and pname = ?";
        List<Product> li = null;

        try {
            li= qr.query(sql, new BeanListHandler<Product>(Product.class),parentId,productName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (li.size() != 0){
            String sql2 = "update product set parentId = ? ,pname = ? ,stats = ? " +
                    "where parentId = ? and pname = ?";
            try {
                qr.update(sql2,parentId,productName,status,parentId,productName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return li;
        }else{
            String sql1 = "insert into product values(null,?,?,?)";
            try {
                qr.update(sql1,parentId,productName,status);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return li;
    }

    //商品上下架
    public Product set_sale_status(String productId, String productStatus) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "update product set status = ? where id = ?";
        Product product = null;
        try {
            product =qr.query(sql, new BeanHandler<Product>(Product.class),productStatus,productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}