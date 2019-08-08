package com.neuedu.service;

import com.neuedu.common.Const;
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

        ResponseCode rs = ResponseCode.successRS(li);
        return rs;
    }

    //用户登录
    public ResponseCode selectOne(String username,String password){
        ResponseCode rs = null;
        if(username == null || username.equals("") || password == null || password.equals("")){
            rs = ResponseCode.defeatedRS(1,"账号或密码错误");
            return rs;
        }
        //查找是否有这样一个用户
        Users u = ud.selectOne(username,password);
        //如果用户不存在
        if(u == null){
            rs = ResponseCode.defeatedRS(1,"账号或密码错误");
            return rs;
        }
        //用户权限
        if(u.getType() != 1){
            rs = ResponseCode.defeatedRS(2,"没有操作权限！");
            return rs;
        }
        rs = ResponseCode.successRS(u);
        return rs;
    }
    //用户禁用
    public ResponseCode selectOne(String uids){
        ResponseCode rs = new ResponseCode();
        if(uids == null || uids.equals("")){
            rs = ResponseCode.defeatedRS(Const.USER_PARAMETER_CODE,Const.USER_PARAMETER_MSG);
            return rs;
        }
        //字符串转数值
        Integer uid = null;
        try {
            uid = Integer.parseInt(uids);
        }catch (Exception e){
            rs = ResponseCode.defeatedRS(105,"输入非法参数");
            return rs;
        }


        //查找是否有这样一个用户
        Users u = ud.selectOne(uid);
        //如果用户不存在
        if(u == null){
            rs = ResponseCode.defeatedRS(Const.USER_NO_CODE,Const.USER_NO_MSG);
            return rs;
        }

        //判断用户禁用情况
        if(u.getStats() == 1){
            rs = ResponseCode.defeatedRS(Const.USER_DISABLE_CODE,Const.USER_DISABLE_MSG);
            return rs;
        }

        //禁用用户
        int row = ud.updateByUid(uid);
        if(row <= 0 ){
            rs = ResponseCode.defeatedRS(106,"用户禁用更新失败");
            return rs;
        }
        rs = ResponseCode.successRS(0,row);
        return rs;
    }
}
