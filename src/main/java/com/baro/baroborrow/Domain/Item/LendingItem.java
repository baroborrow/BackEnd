package com.baro.baroborrow.domain.Item;

import lombok.Data;

@Data
public class LendingItem {
    private String productName;
    private String startDate;
    private String endDate;
    private Double price;
    private String category;
    private String imageUrl;
    private String chatLink;
    private String description;
    private String precautions;

    public LendingItem() {}

    public LendingItem(String productName, String startDate, String endDate, Double price, String category, String imageUrl, String chatLink, String description, String precautions) {
        this.productName = productName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.chatLink = chatLink;
        this.description = description;
        this.precautions = precautions;
    }



}
