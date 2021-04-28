package com.agricultural.form;

import lombok.Data;

@Data
public class AddProductForm {
    private String name;

    private String price;

    private String produce;

    private Integer num;

    private String labels;

    private String img;

    private String context;

    private String contextMd;
}
