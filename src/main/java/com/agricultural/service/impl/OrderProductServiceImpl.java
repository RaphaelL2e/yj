package com.agricultural.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.agricultural.form.ListOrderProductForm;
import com.agricultural.pojo.OrderProduct;
import com.agricultural.dao.OrderProductMapper;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IOrderProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lee
 * @since 2021-04-12
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements IOrderProductService {

    @Override
    public ServerResponse ListOrderProduct(ListOrderProductForm listOrderProductForm) {
        QueryWrapper<OrderProduct> queryWrapper = new QueryWrapper<>();
        OrderProduct orderProduct = new OrderProduct();
        BeanUtil.copyProperties(listOrderProductForm,orderProduct, CopyOptions.create().setIgnoreCase(true).setIgnoreNullValue(true));
        queryWrapper.setEntity(orderProduct);
        IPage<OrderProduct> productIpage = new Page<>(listOrderProductForm.getPageNum(),listOrderProductForm.getPageSize());
        IPage<OrderProduct> pagelist = this.page(productIpage,queryWrapper);
        return ServerResponse.createBySuccess(pagelist);
    }
}
