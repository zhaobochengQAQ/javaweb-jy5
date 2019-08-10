package com.neuedu.controller;

import com.neuedu.common.ResponseCode;
import com.neuedu.service.CategoryService;
import com.neuedu.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/category/*")
public class CategoryController extends HttpServlet {

    CategoryService cs = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String path = PathUTil.getPath(pathInfo);

        //处理统一返回对象
        ResponseCode rs = null;
        //判断一下是什么样的请求
        switch (path){
            case "clist"://先获取商品分类列表
                rs = clistDo(request);
                break;
            case "plist"://输入商品分类id后，获取品类子节点
                rs = plistDo(request);
                break;
            case "add_category"://添加商品分类
                rs = add_categoryDo(request);
                break;
            case "set_category_name"://添加商品分类
                rs = set_category_nameDo(request);
                break;
        }

        //返回响应数据
        response.getWriter().write(rs.toString());
    }

    //修改商品分类名称
    private ResponseCode set_category_nameDo(HttpServletRequest request) {
        ResponseCode rs =new ResponseCode();

        //获取参数
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        //调用业务层处理业务
        rs = cs.set_category_name(categoryId,categoryName);
        return rs;
    }

    //添加商品分类
    private ResponseCode add_categoryDo(HttpServletRequest request) {
        ResponseCode rs =new ResponseCode();

        //获取参数
        String categoryName = request.getParameter("categoryName");
        String parentId = request.getParameter("parentId");
        //调用业务层处理业务
        rs = cs.add_category(parentId,categoryName);
        return rs;

    }

    //获取分类列表
    private ResponseCode clistDo(HttpServletRequest request) {
        ResponseCode rs =new ResponseCode();

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum =request.getParameter("pageNum");

        //调用业务层处理业务
        rs = cs.selectAll(pageSize, pageNum);
        return rs;
    }

    //获取品类子节点列表
    private ResponseCode plistDo(HttpServletRequest request){

        ResponseCode rs =new ResponseCode();

        //获取参数
        String categoryId = request.getParameter("categoryId");

        //调用业务层处理业务
        rs = cs.selectSonAll(categoryId);

        return rs;

    }


}