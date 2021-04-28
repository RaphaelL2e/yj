package com.agricultural.form;

import lombok.Data;

@Data
public class UpdatePasswordForm {
    private String username;
    private String password;
    private String newPassword;
}
