package com.baro.baroborrow.Domain;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.signature.qual.Identifier;

import java.util.Date;

@Getter
@Setter
public class User {
    @Identifier
    private String user_id;
    private String kakao_id;
    private String password;
    private String username;
    private String address;
    private Date created_at;
    private Date updated_at;
    private int point;
    private double latitude;
    private double longitude;

    public User() {
    }

    public User (String user_id, String kakao_id, String password, String username,double latitude, double longitude) {
        this.user_id = user_id;
        if(kakao_id != null) {
            this.kakao_id = kakao_id;
        }
        this.password = password;
        this.username = username;
        this.created_at = new Date();
        this.point = 0;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
