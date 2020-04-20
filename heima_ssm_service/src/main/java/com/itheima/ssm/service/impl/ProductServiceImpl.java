package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.ProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-24 12:54
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 查询全部商品
     * @return
     */
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    /**
     * 保存商品
     * @param product
     * @return
     */
    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);

    }
}
