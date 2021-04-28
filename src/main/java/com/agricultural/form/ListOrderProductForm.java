package com.agricultural.form;

import lombok.Data;

@Data
public class ListOrderProductForm {
    private String orderId;

    Integer pageSize = 10;
    Integer pageNum = 0;
}
