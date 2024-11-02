package com.baro.baroborrow.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductAddDTO {
    private String board_id;
    private String user_id;
    private String user_id2;
    private String category;
    private Date borrow_date;
    private Date return_date;
}
