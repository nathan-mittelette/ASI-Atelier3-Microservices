package com.asi.lib.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDTO implements Serializable {

    private Long id;
    private String lastname;
    private String firstname;
    private String email;
    private Long money;

}

