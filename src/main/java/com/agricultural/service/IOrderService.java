package com.agricultural.service;

import com.agricultural.form.ListProductByUserIdForm;
import com.agricultural.pojo.Orders;
import com.agricultural.response.ServerResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee
 * @since 2021-04-12
 */
public interface IOrderService extends IService<Orders> {

    ServerResponse addOrder(Orders order);

    ServerResponse listOrder(ListProductByUserIdForm listProductByUserIdForm);

}
