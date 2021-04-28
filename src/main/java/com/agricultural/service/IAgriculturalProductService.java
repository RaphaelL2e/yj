package com.agricultural.service;

import com.agricultural.form.ListProductForm;
import com.agricultural.pojo.AgriculturalProduct;
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
public interface IAgriculturalProductService extends IService<AgriculturalProduct> {

    ServerResponse add(AgriculturalProduct agriculturalProduct);

    ServerResponse updateProduct(AgriculturalProduct agriculturalProduct);

    ServerResponse deleteProduct(AgriculturalProduct agriculturalProduct);

    ServerResponse listProduct(ListProductForm listProductForm);
}
