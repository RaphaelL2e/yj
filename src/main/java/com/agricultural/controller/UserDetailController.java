package com.agricultural.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.agricultural.constants.UserConstant;
import com.agricultural.form.GetUserDetailForm;
import com.agricultural.form.UpdateUserDetailForm;
import com.agricultural.pojo.UserDetail;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IUserDetailService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import com.agricultural.controller.support.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lee
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/userDetail")
public class UserDetailController extends BaseController {
    IUserDetailService userDetailService;

    public UserDetailController(IUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    public ServerResponse updateUserDetail(@RequestBody UpdateUserDetailForm updateUserDetailForm, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        UserDetail ud = new UserDetail();
        ud.setUserId(userId.toString());
        BeanUtil.copyProperties(updateUserDetailForm, ud, CopyOptions.create().setIgnoreError(true).setIgnoreNullValue(true));

        return userDetailService.updateUserDetail(ud);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/get/{userId}")
    public ServerResponse getUserDetail(@PathVariable String userId){
        return userDetailService.getUserDetail(userId);
    }
}
