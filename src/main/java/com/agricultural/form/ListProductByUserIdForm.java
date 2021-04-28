package com.agricultural.form;

import lombok.Data;

@Data
public class ListProductByUserIdForm {
    String userId;

    String orderBy;

    Boolean order;

    Integer pageSize = 10;
    Integer pageNum = 0;
}
