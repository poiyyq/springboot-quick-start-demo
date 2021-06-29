package com.winnie.biz.user.service;


import com.winnie.biz.user.model.dto.UserReqDTO;
import com.winnie.biz.user.model.dto.UserResDTO;

import java.util.List;

public interface IUserService {
    UserResDTO getUser(Integer userId);

    boolean createUser(UserReqDTO userReqDTO);

    List<UserResDTO> getUserList();

    boolean updateUser(UserReqDTO userReqDTO);

    boolean deleteUser(Integer id);
}
