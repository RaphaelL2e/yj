package com.agricultural.controller;


import com.agricultural.form.AddOrderProductForm;
import com.agricultural.form.ListOrderProductForm;
import com.agricultural.pojo.OrderProduct;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IOrderProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.agricultural.controller.support.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lee
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/orderProduct")
public class OrderProductController extends BaseController {

    private final
    IOrderProductService orderProductService;

    public OrderProductController(IOrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @ApiOperation("添加产品到订单（购物车）")
    @PostMapping("/add")
    public ServerResponse AddOrderProduct(@RequestBody AddOrderProductForm addOrderProductForm) {
        try {
            orderProductService.save(addOrderProductForm);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }

    @ApiOperation("更新产品数量到订单（购物车）")
    @PutMapping("/update")
    public ServerResponse UpdateOrderProduct(@RequestBody OrderProduct orderProduct) {
        QueryWrapper<OrderProduct> queryWrapper = new QueryWrapper<OrderProduct>()
                .eq("order_id", orderProduct.getOrderId())
                .eq("product_id", orderProduct.getProductId());
        try {
            orderProductService.update(orderProduct, queryWrapper);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }

    @ApiOperation("删除产品从订单（购物车）")
    @DeleteMapping("/delete")
    public ServerResponse DeleteOrderProduct(@RequestBody OrderProduct orderProduct) {
        QueryWrapper<OrderProduct> queryWrapper = new QueryWrapper<OrderProduct>()
                .eq("order_id", orderProduct.getOrderId())
                .eq("product_id", orderProduct.getProductId());
        try {
            orderProductService.remove(queryWrapper);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }

    @ApiOperation("获取产品从订单（购物车）")
    @PostMapping("/listByOrderId")
    public ServerResponse ListOrderProduct(@RequestBody ListOrderProductForm listOrderProductForm){
        return orderProductService.ListOrderProduct(listOrderProductForm);
    }
}
