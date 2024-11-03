package com.baro.baroborrow.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.baro.baroborrow.domain.Item.LendingItem;
import org.springframework.web.multipart.MultipartFile;;

@Data
@Getter
@NoArgsConstructor
public class LendingItemServerDto {
    private String productName;
    private String startDate;
    private String endDate;
    private Double price;
    private String category;
    private MultipartFile image;
    private String chatLink;
    private String description;
    private String precautions;


    public static void entityToDto(LendingItem item, String base64Image) {

    }
}
