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


@WebServlet("/manage/product/*")//产品控制层
public class ProductController extends HttpServlet {
    private ProductService ps = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String path = PathUTil.getPath(pathInfo);

        ResponseCode rs = null;
        switch (path) {
            case "search":
                rs = searchDo(request);//搜索商品，根据商品名查
                break;
            case "add_product"://添加商品
                rs = add_productDo(request);
                break;
            case "detail"://商品详情，根据商品id查
                rs = detailDo(request);
                break;
            case "set_sale_status"://商品上下架，修改商品状态
                rs = set_sale_statusDo(request);
                break;
            case "plist"://商品列表
                rs = plistDo(request);
                break;

        }

        response.getWriter().write(rs.toString());

    }

    //商品上下架
    private ResponseCode set_sale_statusDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
        String productId = request.getParameter("productId");
        String productStatus = request.getParameter("productStatus");

        rs = ps.set_sale_status(productId, productStatus);
        return rs;
    }

    //搜索商品
    private ResponseCode searchDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        String productName = request.getParameter("productName");
        rs = ps.search_product(productName);
        return rs;
    }

    //商品详情
    private ResponseCode detailDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        String productId = request.getParameter("productId");
        rs = ps.product_detail(productId);
        return rs;
    }

    //添加/更新商品
    private ResponseCode add_productDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        //获取参数
        String productName = request.getParameter("productName");
        String parentId = request.getParameter("parentId");
        String status = request.getParameter("status");

        rs = ps.add_product(productName, parentId, status);
        return rs;
    }

    //获取商品列表
    private ResponseCode plistDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = ps.selectAll(pageSize, pageNum);

        return rs;

    }


}

