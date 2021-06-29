package com.winnie.biz.user.service.impl;

import com.winnie.biz.user.dao.UserDao;
import com.winnie.biz.user.model.dto.UserReqDTO;
import com.winnie.biz.user.model.dto.UserResDTO;
import com.winnie.biz.user.model.entity.UserDO;
import com.winnie.biz.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User服务层
 *
 * @author yanyq
 * @date 2021年06月28日
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    /**
     * 通过用户id获取数据
     *
     * @param id
     * @return
     */
    @Override
    public UserResDTO getUser(Integer id) {
        UserResDTO userResDTO = new UserResDTO();
        UserDO userDO = userDao.getUser(id);
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userResDTO);
        }
        return userResDTO;
    }

    /**
     * 创建新用户
     *
     * @param userReqDTO
     */
    @Override
    public boolean createUser(UserReqDTO userReqDTO) {
        Integer resultSize = userDao.saveUser(userReqDTO);
        if (resultSize > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public List<UserResDTO> getUserList() {
        List<UserResDTO> userResDTOS = new ArrayList<>();
        List<UserDO> userDOS = userDao.getUserList();
        if (userDOS != null && userDOS.size() > 0) {
            userResDTOS = userDOS.stream().map(userDO -> {
                UserResDTO userResDTO = new UserResDTO();
                BeanUtils.copyProperties(userDO, userResDTO);
                return userResDTO;
            }).collect(Collectors.toList());
        }
        return userResDTOS;
    }

    /**
     * 更新用户
     *
     * @param userReqDTO
     * @return
     */
    @Override
    public boolean updateUser(UserReqDTO userReqDTO) {
        Integer resultSize = userDao.updateUser(userReqDTO);
        return resultSize >= 0 ? true : false;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUser(Integer id) {
        Integer resultSize = userDao.deleteUser(id);
        return resultSize >= 0 ? true : false;
    }
}
