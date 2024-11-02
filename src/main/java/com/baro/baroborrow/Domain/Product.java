package com.baro.baroborrow.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product {
    private String product_id;
    private String user_id; //빌린사람
    private String user_id2;    //빌려준사람
    private String category;
    private Date borrow_date;
    private Date return_date;
    private Boolean returned;

    public Product(){}

    public Product(String product_id, String user_id, String user_id2, String category, Date borrow_date, Date return_date) {
        this.product_id = product_id;
        this.user_id = user_id;
        this.user_id2 = user_id2;
        this.category = category;
        this.borrow_date = borrow_date;
        this.returned = false;
        this.return_date = return_date;
    }
}
