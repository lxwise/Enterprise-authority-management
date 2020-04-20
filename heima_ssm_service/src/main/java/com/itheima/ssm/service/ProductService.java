package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-24 12:52
 * 产品业务层接口
 */
public interface ProductService {

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAll();

    /**
     * 保存商品
     * @param product
     */
    void saveProduct(Product product);
}
