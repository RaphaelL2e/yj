package com.agricultural.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.agricultural.constants.UserConstant;
import com.agricultural.form.AddProductForm;
import com.agricultural.form.DeleteProductForm;
import com.agricultural.form.ListProductForm;
import com.agricultural.form.UpdateProductForm;
import com.agricultural.pojo.AgriculturalProduct;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IAgriculturalProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.agricultural.controller.support.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lee
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/agriculturalProduct")
public class AgriculturalProductController extends BaseController {
    private IAgriculturalProductService agriculturalProductService;

    public AgriculturalProductController(IAgriculturalProductService agriculturalProductService) {
        this.agriculturalProductService = agriculturalProductService;
    }

    @ApiOperation("添加产品")
    @PostMapping("/add")
    public ServerResponse AddProduct(@RequestBody AddProductForm addProductForm,HttpServletRequest request) {
        AgriculturalProduct agriculturalProduct = new AgriculturalProduct();
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        agriculturalProduct.setUserId(userId.toString());
        BeanUtil.copyProperties(addProductForm, agriculturalProduct, CopyOptions.create().setIgnoreError(true).setIgnoreNullValue(true));
        return agriculturalProductService.add(agriculturalProduct);
    }

    @ApiOperation("更新农产品")
    @PutMapping("/update")
    public ServerResponse UpdateProduct(@RequestBody UpdateProductForm updateProductForm, HttpServletRequest request){
        AgriculturalProduct agriculturalProduct = new AgriculturalProduct();
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        agriculturalProduct.setUserId(userId.toString());
        BeanUtil.copyProperties(updateProductForm, agriculturalProduct, CopyOptions.create().setIgnoreError(true).setIgnoreNullValue(true));
        return agriculturalProductService.updateProduct(agriculturalProduct);
    }

    @ApiOperation("删除农产品")
    @DeleteMapping("/delete")
    public ServerResponse DeleteProduct(@RequestBody DeleteProductForm deleteProductForm, HttpServletRequest request){
        AgriculturalProduct agriculturalProduct = new AgriculturalProduct();
        Long userId = (Long) request.getSession().getAttribute(UserConstant.USER_SESSION_NAME);
        agriculturalProduct.setUserId(userId.toString());
        BeanUtil.copyProperties(deleteProductForm, agriculturalProduct, CopyOptions.create().setIgnoreError(true).setIgnoreNullValue(true));
        return agriculturalProductService.deleteProduct(agriculturalProduct);
    }

    @ApiOperation("分页条件获取农产品列表")
    @PostMapping("/list")
    public ServerResponse ListProduct(@RequestBody ListProductForm listProductForm){

        return agriculturalProductService.listProduct(listProductForm);
    }

    @ApiOperation("获取推荐农产品列表")
    @PostMapping("/love")
    public ServerResponse ListProductByLove(){
        ListProductForm listProductForm = new ListProductForm();
        final double d = Math.random();
        final int i = (int)(d*100);
        listProductForm.setPageNum(i);
        listProductForm.setPageSize(10);
        return agriculturalProductService.listProduct(listProductForm);
    }
}
