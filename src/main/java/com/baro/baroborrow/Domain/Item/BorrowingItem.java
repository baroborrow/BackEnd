package com.baro.baroborrow.domain.Item;

import lombok.Data;

@Data
public class BorrowingItem {
    private String productName;
    private String startDate;
    private String endDate;
    private String chatLink;

    public BorrowingItem() {}

    public BorrowingItem(String productName, String startDate, String endDate, String chatLink) {
        this.productName = productName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.chatLink = chatLink;
    }


}
