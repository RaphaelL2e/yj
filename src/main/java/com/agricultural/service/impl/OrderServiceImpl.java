package com.agricultural.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.agricultural.form.ListProductByUserIdForm;
import com.agricultural.pojo.AgriculturalProduct;
import com.agricultural.pojo.Order;
import com.agricultural.dao.OrderMapper;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IOrderService;
import com.agricultural.util.SnowflakeIdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lee
 * @since 2021-04-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final
    OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public ServerResponse addOrder(Order order) {
        SnowflakeIdWorker sw = new SnowflakeIdWorker(2, 1);
        order.setId(String.valueOf(sw.nextId()));
        order.setCreateAt(LocalDateTime.now());
        try {
            orderMapper.insert(order);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess(order);
    }

    @Override
    public ServerResponse listOrder(ListProductByUserIdForm listProductByUserIdForm) {
        QueryWrapper<Order> articleQueryWrapper = new QueryWrapper<>();
        if (null != listProductByUserIdForm.getUserId()) {
            articleQueryWrapper = articleQueryWrapper.eq("user_id", listProductByUserIdForm.getUserId());
        }
        if (null != listProductByUserIdForm.getOrderBy()) {
            articleQueryWrapper.orderBy(true, listProductByUserIdForm.getOrder(), listProductByUserIdForm.getOrderBy());
        } else {
            articleQueryWrapper.orderByDesc("create_at");
        }

        Order order = new Order();
        BeanUtil.copyProperties(listProductByUserIdForm, order, CopyOptions.create().setIgnoreNullValue(true).setIgnoreCase(true));
        IPage<Order> orderIPage = new Page<>(listProductByUserIdForm.getPageNum(), listProductByUserIdForm.getPageSize());
        IPage<Order> orderIPage1 = this.page(orderIPage, articleQueryWrapper);
        return ServerResponse.createBySuccess(orderIPage1);
    }
}
