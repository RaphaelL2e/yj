package com.agricultural.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lee
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private Integer productId;

    private Integer count;


}
