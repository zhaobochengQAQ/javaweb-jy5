package com.neuedu.dao;

import com.neuedu.pojo.Product;
import com.neuedu.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class ProductDao {
    public List<Product> selectAll() {
        QueryRunner q = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from product";
        List<Product> li = null;
        try {
            li = q.query(sql,new BeanListHandler<Product>(Product.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return li;
    }
}
