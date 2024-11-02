package com.baro.baroborrow.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardBorrow {
    private String board_id;
    private String user_id;
    private String title;
    private Date start_date;
    private Date end_date;
    private String open_chat;
    private Date created_at;

    public BoardBorrow() {}

    public BoardBorrow(String user_id, String title, Date start_date, Date end_date, String open_chat) {
        this.user_id = user_id;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.open_chat = open_chat;
        this.created_at = new Date();
    }

}
