package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-24 12:50
 * 商品持久层接口
 */
@Repository
public interface ProductDao {

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product findById(Integer id);
    /**
     * 查询所有商品
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();

    /**
     * 保存产品
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);
}
