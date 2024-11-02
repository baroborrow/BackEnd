package com.baro.baroborrow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String username;
    private String password;
    private String mail_address;
    private String kakao_id;
}
