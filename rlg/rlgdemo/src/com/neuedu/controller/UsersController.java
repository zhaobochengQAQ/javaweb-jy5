package com.neuedu.controller;

import com.neuedu.common.ResponseCode;
import com.neuedu.pojo.Users;
import com.neuedu.service.UserService;
import com.neuedu.utils.PathUTil;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@WebServlet("/manage/user/*")
public class UsersController extends HttpServlet {
    private UserService uc = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");


        String pathInfo = request.getPathInfo();
        String path = PathUTil.getPath(pathInfo);


        ResponseCode rs =null;
        switch (path){
            case "list":
                rs = listDo(request);
                break;
            case "login":
                rs = loginDo(request);
                break;
        }
        response.getWriter().write(rs.toString());
    }
    private ResponseCode listDo(HttpServletRequest request){
        ResponseCode rs = new ResponseCode();
        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("user");
        if(user == null){
            rs.setStatus(3);
            rs.setMag("请登录后操作");
            return rs;
        }
        if(user.getType() != 1){
            rs.setStatus(4);
            rs.setMag("没有操作权限");
            return rs;
        }
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        rs = uc.selectAll(pageSize,pageNum);
        return rs;
    }
    private ResponseCode loginDo(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ResponseCode rs = uc.selectOne(username,password);
        HttpSession session = request.getSession();
        session.setAttribute("user",rs.getData());
        return rs;
    }
}









