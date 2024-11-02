package com.baro.baroborrow.Domain;

import com.baro.baroborrow.Enum.CategoryId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Board {
    private String board_id;
    private String user_id;
    private String title;
    private String description;
    private int price;
    private Date start_date;
    private Date end_date;
    private int views;
    private String open_chat;
    private String warning;
    private Date created_at;
    private String category;

    public Board(){}

    public Board(String user_id, String title, Date start_date, Date end_date, int price, String category, String open_chat, String description, String warning){
        this.user_id = user_id;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.description = description;
        this.open_chat = open_chat;
        this.warning = warning;
        this.views = 0;
        this.created_at = new Date();
        this.category = category;
    }


}
