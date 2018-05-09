package com.simple.shopping.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
public class UserJoinForm {

    @NotNull
    @Size(min = 2, max = 10)
    private String name;
    @NotNull
    @Size(min = 2, max = 50)
    private String email;
    @NotNull
    @Size(min = 2, max = 100)
    private String address;
    @NotNull
    @Size(min = 11, max = 11)
    private String phone;
    @NotNull
    @Size(min = 2, max = 15)
    private String password;
    @NotNull
    @Size(min = 2, max = 15)
    private String rePassword;
}
