package com.neuedu.dao;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.neuedu.pojo.Order;
import com.neuedu.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    public List<Order> selectAll(String pageSize, String pageNum) {

        ComboPooledDataSource co = PoolUTil.getCom();
        QueryRunner qr = new QueryRunner(co);
        String sql = "select * from order ";
        List<Order> li = null;
        try {
            li= qr.query(sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }
}
