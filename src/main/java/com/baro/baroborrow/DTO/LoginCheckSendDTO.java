package com.baro.baroborrow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCheckSendDTO {
    private Boolean success;
    private String user_id;

    public LoginCheckSendDTO(Boolean success, String user_id) {
        this.success = success;
        this.user_id = user_id;
    }
}
