package com.baro.baroborrow.domain.Item;

import com.baro.baroborrow.DTO.LendingItemServerDto;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
public class LendingItem {
    private String productName;
    private String startDate;
    private String endDate;
    private Double price;
    private String category;
    private String image;
    private String chatLink;
    private String description;
    private String precautions;

    public LendingItem() {}

    public LendingItem(LendingItemServerDto item, String image) {
        this.productName = item.getProductName();
        this.startDate = item.getStartDate();
        this.endDate = item.getEndDate();
        this.price = item.getPrice();
        this.category = item.getCategory();
        this.image = image;
        this.chatLink = item.getChatLink();
        this.description = item.getDescription();
        this.precautions = item.getPrecautions();
    }



}
