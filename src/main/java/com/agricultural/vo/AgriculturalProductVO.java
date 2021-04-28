package com.agricultural.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgriculturalProductVO {
    private Integer id;

    private String name;

    private String price;

    private String introduction;

    private Integer num;

    private Integer sales;

    private String labels;

    private String img;

    private String userId;

    private LocalDateTime createAt;
}
