package com.baro.baroborrow.Domain;

import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.Setter;
import com.baro.baroborrow.Enum.CategoryId;

@Getter
@Setter
public class Product {
    private String product_id;
    private String user_id; //빌린사람
    private String user_id2;    //빌려준사람
    private CategoryId category;
    private Timestamp borrow_date;
    private Timestamp return_date;
    private Boolean returned;

}
