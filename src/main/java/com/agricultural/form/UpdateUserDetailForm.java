package com.agricultural.form;

import com.agricultural.pojo.UserDetail;
import lombok.Data;

@Data
public class UpdateUserDetailForm{
    private String name;

    private String avatar;

    private String payAddress;

    private String sellAddress;

    private String love;
}
