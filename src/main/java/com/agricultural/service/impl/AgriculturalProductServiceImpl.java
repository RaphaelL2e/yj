package com.agricultural.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.agricultural.form.ListProductForm;
import com.agricultural.pojo.AgriculturalProduct;
import com.agricultural.dao.AgriculturalProductMapper;
import com.agricultural.pojo.User;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IAgriculturalProductService;
import com.agricultural.vo.AgriculturalProductVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lee
 * @since 2021-04-12
 */
@Service
public class AgriculturalProductServiceImpl extends ServiceImpl<AgriculturalProductMapper, AgriculturalProduct> implements IAgriculturalProductService {
    private AgriculturalProductMapper agriculturalProductMapper;

    public AgriculturalProductServiceImpl(AgriculturalProductMapper agriculturalProductMapper) {
        this.agriculturalProductMapper = agriculturalProductMapper;
    }

    @Override
    public ServerResponse add(AgriculturalProduct agriculturalProduct) {
        try {
            agriculturalProduct.setCreateAt(LocalDateTime.now());
            agriculturalProductMapper.insert(agriculturalProduct);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess(agriculturalProduct);
    }

    @Override
    public ServerResponse updateProduct(AgriculturalProduct agriculturalProduct) {
        QueryWrapper<AgriculturalProduct> agriculturalProductQueryWrapper = new QueryWrapper<>();
        AgriculturalProduct ap = new AgriculturalProduct();
        ap.setUserId(agriculturalProduct.getUserId());
        ap.setId(agriculturalProduct.getId());
        try {
            agriculturalProductMapper.update(agriculturalProduct, agriculturalProductQueryWrapper);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }

        return ServerResponse.createBySuccess(agriculturalProductMapper.selectById(agriculturalProduct.getId()));
    }

    @Override
    public ServerResponse deleteProduct(AgriculturalProduct agriculturalProduct) {
        QueryWrapper<AgriculturalProduct> agriculturalProductQueryWrapper = new QueryWrapper<>();
        AgriculturalProduct ap = new AgriculturalProduct();
        ap.setUserId(agriculturalProduct.getUserId());
        ap.setId(agriculturalProduct.getId());
        agriculturalProductQueryWrapper.setEntity(ap);
        try {
            agriculturalProductMapper.delete(agriculturalProductQueryWrapper);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse listProduct(ListProductForm listProductForm) {
        QueryWrapper<AgriculturalProduct> articleQueryWrapper = new QueryWrapper<>();
        if (null != listProductForm.getUserId()) {
            articleQueryWrapper = articleQueryWrapper.eq("user_id", listProductForm.getUserId());
        }
        if (null != listProductForm.getOrderBy()) {
            articleQueryWrapper.orderBy(true, listProductForm.getOrder(), listProductForm.getOrderBy());
        } else {
            articleQueryWrapper.orderByDesc("create_at");
        }


        AgriculturalProduct ap = new AgriculturalProduct();
        BeanUtil.copyProperties(listProductForm, ap, CopyOptions.create().setIgnoreNullValue(true).setIgnoreCase(true));
        IPage<AgriculturalProduct> agriculturalProductPage = new Page<>(listProductForm.getPageNum(), listProductForm.getPageSize());
        IPage<AgriculturalProduct> agriculturalProductIPage = this.page(agriculturalProductPage, articleQueryWrapper);
        Page<AgriculturalProductVO> page = new Page<>();
        List<AgriculturalProductVO> agriculturalProductVOArrayList = new ArrayList<>();
        for (AgriculturalProduct agriculturalProduct : agriculturalProductIPage.getRecords()) {
            AgriculturalProductVO agriculturalProductVO = new AgriculturalProductVO();
            BeanUtil.copyProperties(agriculturalProduct, agriculturalProductVO, CopyOptions.create().setIgnoreCase(true).setIgnoreNullValue(true));
            agriculturalProductVOArrayList.add(agriculturalProductVO);
        }
        page.setRecords(agriculturalProductVOArrayList);
        page.setCurrent(agriculturalProductIPage.getCurrent());
        page.setSize(agriculturalProductIPage.getSize());
        page.setTotal(agriculturalProductIPage.getTotal());
        return ServerResponse.createBySuccess(page);
    }
}
