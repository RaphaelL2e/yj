package com.agricultural.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.agricultural.constants.UserConstant;
import com.agricultural.form.*;
import com.agricultural.pojo.Orders;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.agricultural.controller.support.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lee
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    final
    IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }


    @ApiOperation("创建订单")
    @PostMapping("/add")
    public ServerResponse AddOrder(@RequestBody AddOrderForm addOrderForm, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        Orders order = new Orders();
        order.setUserId(userId.toString());
        return orderService.addOrder(order);
    }


    @ApiOperation("更新订单地址")
    @PutMapping("/update")
    public ServerResponse UpdateOrder(@RequestBody UpdateOrderForm updateOrderForm, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        Orders order = new Orders();
        order.setUserId(userId.toString());
        BeanUtil.copyProperties(updateOrderForm, order, CopyOptions.create().setIgnoreNullValue(true).setIgnoreCase(true));
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", updateOrderForm.getId());
        try {
            orderService.update(order, queryWrapper);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess(orderService.getById(order.getId()));
    }

    @ApiOperation("删除、取消订单")
    @DeleteMapping("/delete")
    public ServerResponse DeleteOrder(@RequestBody DeleteOrderForm deleteOrderForm, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        Orders order = new Orders();
        order.setUserId(userId.toString());
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", deleteOrderForm.getId());
        queryWrapper.eq("status",0);
        order.setStatus(-1);
        try {
            orderService.update(order,queryWrapper);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }

    @ApiOperation("完成订单")
    @PostMapping("/finish")
    public ServerResponse FinishOrder(FinishOrderForm finishOrderForm,HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        Orders order = new Orders();
        order.setUserId(userId.toString());
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", finishOrderForm.getId());
        queryWrapper.eq("status",0);
        order.setStatus(1);
        try {
            orderService.update(order,queryWrapper);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }
    @ApiOperation("订单列表")
    @PostMapping("/list")
    public ServerResponse ListProductByUserId(ListProductByUserIdForm listProductByUserIdForm, HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        listProductByUserIdForm.setUserId(userId.toString());
        return orderService.listOrder(listProductByUserIdForm);
    }

    @ApiOperation("根据id获取订单")
    @PostMapping("/get")
    public ServerResponse GetOrderByID(@RequestBody GetOrderByIDForm getOrderByIDForm){
        return ServerResponse.createBySuccess(orderService.getById(getOrderByIDForm.getId()));
    }
}
