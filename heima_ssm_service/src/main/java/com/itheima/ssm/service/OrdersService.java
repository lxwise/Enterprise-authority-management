package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-25 12:00
 */
public interface OrdersService {
    /**
     * 查询所有订单
     * @return
     */

   public List<Orders> findAll(int page,int size);

    /**
     * 根据id查询
     * @param id
     * @return
     */
   public Orders findById(Integer id);

}
