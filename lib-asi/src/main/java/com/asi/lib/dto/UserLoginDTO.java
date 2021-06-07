package com.asi.lib.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginDTO implements Serializable {

    private String login;
    private String password;

}
