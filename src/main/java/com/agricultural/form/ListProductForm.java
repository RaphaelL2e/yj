package com.agricultural.form;

import lombok.Data;

@Data
public class ListProductForm {
    String userId;

    String orderBy;

    Boolean order;

    Integer pageSize = 10;
    Integer pageNum = 0;
}
