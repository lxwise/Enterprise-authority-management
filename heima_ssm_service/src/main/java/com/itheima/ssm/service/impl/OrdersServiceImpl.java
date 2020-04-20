package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.OrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-25 12:00
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService{

   @Autowired
   private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page,int size) {

        //pageNum是页码值，pagesize是每页显示的条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(Integer id) {
        return ordersDao.findById(id);
    }
}
