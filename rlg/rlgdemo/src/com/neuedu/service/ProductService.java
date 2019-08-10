package com.neuedu.service;

import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.dao.ProductDao;
import com.neuedu.pojo.Product;

import java.util.List;


public class ProductService {
    ProductDao pd = new ProductDao();

    //搜索商品
    public ResponseCode search_product(String productName) {
        ResponseCode rs = new ResponseCode();

        if (productName == null || productName.equals("")) {
            rs.setStatus(108);
            rs.setMag("请输入您要查询的商品名！");
            return rs;
        }

        List<Product> li = pd.search_product(productName);

        if (li.size() == 0) {
            rs.setStatus(Const.PRODUCT_SEARCH_CODE);
            rs.setMag(Const.PRODUCT_SEARCH_MSG);
            return rs;
        }

        rs = ResponseCode.successRS(li);
        return rs;
    }

    //商品详情
    public ResponseCode product_detail(String productId) {
        ResponseCode rs = new ResponseCode();

        if (productId == null || productId.equals("")) {
            rs.setStatus(108);
            rs.setMag("请输入您要查询的商品id！");
            return rs;
        }

        Product product = pd.product_detail(productId);

        if (product == null) {
            rs.setStatus(Const.PRODUCT_SEARCH_CODE);
            rs.setMag(Const.PRODUCT_SEARCH_MSG);
            return rs;
        }

        rs.setStatus(0);
        rs.setData(product);
        rs.setMag("您已成功查询到该商品！");
        return rs;
    }

    //增加/更新商品
    public ResponseCode add_product(String productName, String parentId, String status) {

        ResponseCode rs = new ResponseCode();
        //设置默认值
        if (parentId == null || parentId.equals("")) {
            parentId = "0";
        }
        if (productName == null || productName.equals("")) {
            rs.setStatus(0);
            rs.setMag("请输入您要添加的商品名称");
            return rs;
        }

        List<Product> li = pd.add_product(parentId, productName, status);
        //如果商品分类存在
        if (li.size() != 0) {
            rs.setStatus(Const.PRODUCT_HAVE_CODE);
            rs.setMag(Const.PRODUCT_HAVE_MSG);
            return rs;
        }

        rs.setStatus(0);
        rs.setMag("商品添加成功");
        return rs;
    }

    //商品上下架，修改商品状态
    public ResponseCode set_sale_status(String productId, String productStatus) {
        ResponseCode rs = new ResponseCode();
        //设置默认值
        if (productId == null || productId.equals("")) {
            rs.setStatus(109);
            rs.setMag("商品id为空，请重新输入");
            return rs;
        }
        if (productStatus == null || productStatus.equals("")) {
            rs.setStatus(108);
            rs.setMag("请输入您要添加的商品名称");
            return rs;
        }
        Product product = pd.set_sale_status(productId, productStatus);

        return rs;
    }


    //商品列表
    public ResponseCode selectAll(String pageSize, String pageNum) {
        //设置默认值
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }

        //调用数据层
        List<Product> li = pd.selectAll(pageSize, pageNum);

        ResponseCode rs = ResponseCode.successRS(li);

        return rs;
    }
}
