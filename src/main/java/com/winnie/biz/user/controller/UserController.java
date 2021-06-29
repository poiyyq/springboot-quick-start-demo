package com.winnie.biz.user.controller;

import com.winnie.biz.user.model.dto.UserReqDTO;
import com.winnie.biz.user.model.dto.UserResDTO;
import com.winnie.biz.user.model.vo.UserVO;
import com.winnie.biz.user.model.vo.UsernameVO;
import com.winnie.biz.user.service.IUserService;
import com.winnie.common.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * restful-api设计要点1：路由名称使用复数形式
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping
    public ResponseVO<List<UserVO>> getUser(){
        List<UserVO> userVOS = new ArrayList<>();
        List<UserResDTO> userResDTOS = userService.getUserList();
        if(userResDTOS!=null && userResDTOS.size()>0){
            userVOS = userResDTOS.stream().map(userResDTO -> {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(userResDTO, userVO);
                return userVO;
            }).collect(Collectors.toList());
        }
        ResponseVO<List<UserVO>> responseVO = ResponseVO.ok("", userVOS);
        return responseVO;
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseVO<UserVO> getUser(@PathVariable("id") Integer id){
        ResponseVO<UserVO> responseVO;
        UserVO userVO = new UserVO();
        UserResDTO userResDTO = userService.getUser(id);
        if(userResDTO!=null){
            BeanUtils.copyProperties(userResDTO,userVO);
        }
        responseVO = ResponseVO.ok("", userVO);
        return responseVO;
    }

    /**
     * 获取用户ID的名称
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}/username")
    public ResponseVO<UsernameVO> getUsername(@PathVariable("id") Integer id){
        ResponseVO<UsernameVO> responseVO;
        UsernameVO usernameVO = new UsernameVO();
        UserResDTO userResDTO = userService.getUser(id);
        if(userResDTO!=null){
            BeanUtils.copyProperties(userResDTO,usernameVO);
        }
        responseVO = ResponseVO.ok("",usernameVO);
        return responseVO;
    }

    /**
     * 创建用户
     * @return
     */
    @PostMapping
    public ResponseVO<Boolean> createUser(@RequestBody UserReqDTO userReqDTO){
        ResponseVO<Boolean> respVO = new ResponseVO<>();
        boolean result = userService.createUser(userReqDTO);
        if(result){
            respVO = ResponseVO.ok("创建成功");
        } else {
            respVO = ResponseVO.fail("创建失败");
        }
        return respVO;
    }

    /**
     * 更新用户
     * @param id
     * @param userReqDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseVO updateUser(@PathVariable("id") Integer id,@RequestBody UserReqDTO userReqDTO){
        Assert.notNull(id, "用户ID不允许为空");
        userReqDTO.setId(id);
        boolean result = userService.updateUser(userReqDTO);
        if(result){
            return ResponseVO.ok("更新成功");
        } else {
            return ResponseVO.fail("更新失败");
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseVO deleteUser(@PathVariable("id") Integer id){
        Assert.notNull(id, "用户ID不允许为空");
        boolean result = userService.deleteUser(id);
        if(result){
            return ResponseVO.ok("删除成功");
        } else {
            return ResponseVO.fail("删除失败");
        }
    }



}
