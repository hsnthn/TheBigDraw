package com.lottery.bigDraw.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by kafein on 1/15/2015.
 */
public class Coupon implements Serializable{

    private String username;
    private Date date;
    private List<String> playerNumbers;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getPlayerNumbers() {
        return playerNumbers;
    }

    public void setPlayerNumbers(List<String> playerNumbers) {
        this.playerNumbers = playerNumbers;
    }


}
