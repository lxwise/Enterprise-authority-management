package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author l-xin
 * @create 2020-03-25 20:52
 */
public interface MemberDao {

    @Select("select * from member where id=#{id}")
    Member findById(Integer id);
}
