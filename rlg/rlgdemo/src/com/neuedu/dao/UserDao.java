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

   //查找所有用户
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

    //根据用户名和密码查找一个用户
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

    //根据ID查找一个用户
    public Users selectOne(Integer uid){
        QueryRunner q = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from users where id = ?";
        Users u = null;
        try {
            u = q.query(sql,new BeanHandler<Users>(Users.class),uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }

    //根据ID禁用一个用户
    public int updateByUid(Integer uid){
        QueryRunner q = new QueryRunner(PoolUTil.getCom());
        String sql = "update users set stats = 1 where id = ?";
        int row = 0;
        try {
            row = q.update(sql,uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
}









