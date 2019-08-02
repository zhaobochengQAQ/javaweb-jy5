package com.neuedu.service;

import com.neuedu.common.ResponseCode;
import com.neuedu.dao.UserDao;
import com.neuedu.pojo.Users;

import java.util.List;

public class UserService {
    UserDao ud = new UserDao();
    public ResponseCode selectAll(String pageSize, String pageNum) {
        if(pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if(pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }
        List<Users> li = ud.selectAll(pageSize,pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }
    public ResponseCode selectOne(String username,String password){
        ResponseCode rs = new ResponseCode();
        if(username == null || username.equals("") || password == null || password.equals("")){
            rs.setStatus(1);
            rs.setMag("账号或密码错误");
        }
        Users u = ud.selectOne(username,password);
        if(u == null){
            rs.setStatus(1);
            rs.setMag("账号或密码错误");
            return rs;
        }
        if(u.getType() != 1){
            rs.setStatus(2);
            rs.setMag("没有操作权限！");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(u);
        return rs;
    }
}
