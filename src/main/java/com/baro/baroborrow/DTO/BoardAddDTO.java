package com.baro.baroborrow.DTO;

import com.baro.baroborrow.Enum.CategoryId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardAddDTO {
    private String user_id;
    private String title;
    private Date start_date;
    private Date end_date;
    private int price;
    private String category;
    private String open_chat;
    private String description;
    private String warning;

}
