package com.winnie.biz.user.dao;

import com.winnie.biz.user.model.dto.UserReqDTO;
import com.winnie.biz.user.model.entity.UserDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User持久层
 * @author yanyq
 * @date 2021年06月28日
 */
@Repository
public interface UserDao {
    /**
     * 通过用户id获取对象数据
     * @param id
     * @return
     */
    @Select("SELECT * FROM T_USER WHERE ID = #{id}")
    UserDO getUser(Integer id);

    /**
     * 创建用户
     * @param userReqDTO
     * @return
     */
    @Insert("INSERT INTO T_USER(USERNAME) VALUES(#{username})")
    Integer saveUser(UserReqDTO userReqDTO);

    /**
     * 查询用户列表
     * @return
     */
    @Select("SELECT * FROM T_USER")
    List<UserDO> getUserList();

    /**
     * 更新用户
     * @param userReqDTO
     * @return
     */
    @Update("UPDATE T_USER SET USERNAME = #{username} WHERE ID = #{id}")
    Integer updateUser(UserReqDTO userReqDTO);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Delete("DELETE FROM T_USER WHERE ID = #{id}")
    Integer deleteUser(Integer id);
}
