package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-25 20:59
 */
public interface TravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
   public List<Traveller> findByOrdersId(Integer id);

}
