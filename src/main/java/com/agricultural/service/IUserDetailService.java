package com.agricultural.service;

import com.agricultural.form.GetUserDetailForm;
import com.agricultural.form.UpdateUserDetailForm;
import com.agricultural.pojo.UserDetail;
import com.agricultural.response.ServerResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee
 * @since 2021-04-09
 */
public interface IUserDetailService extends IService<UserDetail> {

    ServerResponse updateUserDetail(UserDetail ud);

    ServerResponse getUserDetail(String userId);
}
