package com.baro.baroborrow.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductReturnDTO {
    private String product_id;
    private String username; //빌린사람
    private String username2; //빌려준사람
    private String category;
    private Date borrow_date;
    private Date return_date;

    public ProductReturnDTO(String product_id, String username, String username2, String category, Date borrow_date, Date return_date) {
        this.product_id = product_id;
        this.username = username;
        this.username2 = username2;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.category = category;
    }
}
