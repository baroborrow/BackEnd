package com.baro.baroborrow.Domain;

import com.google.api.client.util.DateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private String board_id;
    private String user_id;
    private String title;
    private String content;
    private int price;
    private DateTime created_date;
    private int views;

}
