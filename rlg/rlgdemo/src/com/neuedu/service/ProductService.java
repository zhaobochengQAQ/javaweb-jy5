package com.neuedu.service;

import com.neuedu.common.ResponseCode;
import com.neuedu.dao.ProductDao;
import com.neuedu.pojo.Product;

import java.util.List;


public class ProductService {
    private ProductDao pd = new ProductDao();

    public ResponseCode getAll() {
        ResponseCode rs = null;
        List<Product> li = pd.selectAll();
        rs = ResponseCode.successRS(li);
        return rs;
    }
}
