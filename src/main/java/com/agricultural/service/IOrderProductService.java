package com.agricultural.service;

import com.agricultural.form.ListOrderProductForm;
import com.agricultural.pojo.OrderProduct;
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
public interface IOrderProductService extends IService<OrderProduct> {

    ServerResponse ListOrderProduct(ListOrderProductForm listOrderProductForm);
}
