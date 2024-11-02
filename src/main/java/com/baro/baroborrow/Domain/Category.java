package com.baro.baroborrow.Domain;


import com.baro.baroborrow.Enum.CategoryId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private CategoryId category_id;
    private String type;
}
