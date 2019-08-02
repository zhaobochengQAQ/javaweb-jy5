package com.neuedu.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.neuedu.pojo.Users;
import com.neuedu.utils.PoolUTil;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public List<Users> selectAll(String pageSize, String pageNum) {
        QueryRunner q = new QueryRunner(PoolUTil.getCom());
        List<Users> li = null;
        try {
            li = q.query("select * from users",new BeanListHandler<Users>(Users.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return li;
    }
    public Users selectOne(String username,String password){
        QueryRunner q = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from users where uname = ? and psd = ?";
        Users u = null;
        try {
            u = q.query(sql,new BeanHandler<Users>(Users.class),username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }
}









