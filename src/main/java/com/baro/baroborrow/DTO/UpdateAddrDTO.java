package com.baro.baroborrow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddrDTO {
    private String user_id;
    private String address;
    private double latitude;
    private double longitude;
}
