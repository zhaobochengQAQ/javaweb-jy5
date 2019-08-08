package com.neuedu.controller;

import com.neuedu.common.ResponseCode;
import com.neuedu.service.ProductService;
import com.neuedu.utils.PathUTil;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProductController",value = "/manage/product/*")
public class ProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String path = PathUTil.getPath(pathInfo);
        ResponseCode rs = null;

        switch (path){
            case "list":
                rs = getAll(request);
                break;
        }
        response.getWriter().write(rs.toString());
    }
    private ProductService ps = new ProductService();

    //查询所有商品
    private ResponseCode getAll(HttpServletRequest request){
        //获取参数

        //调用业务层
        ResponseCode rs = ps.getAll();
        return rs;
    }

}
