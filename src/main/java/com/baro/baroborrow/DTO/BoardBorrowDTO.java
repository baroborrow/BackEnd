package com.baro.baroborrow.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardBorrowDTO {
    private String user_id;
    private String title;
    private Date start_date;
    private Date end_date;
    private String open_chat;
}
