package com.lottery.bigDraw.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by kafein on 1/15/2015.
 */
public class Result implements Serializable {

    private String username;
    private Date couponDate;
    private List<String> playerNumbers;
    private List<String> drawNumbers;
    private Double winnigPrize;
    private Date drawDate;

    public Double getWinnigPrize() {
        return winnigPrize;
    }

    public void setWinnigPrize(Double winnigPrize) {
        this.winnigPrize = winnigPrize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPlayerNumbers() {
        return playerNumbers;
    }

    public void setPlayerNumbers(List<String> playerNumbers) {
        this.playerNumbers = playerNumbers;
    }

    public Date getCouponDate() {
        return couponDate;
    }

    public void setCouponDate(Date couponDate) {
        this.couponDate = couponDate;
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

	public List<String> getDrawNumbers() {
		return drawNumbers;
	}

	public void setDrawNumbers(List<String> drawNumbers) {
		this.drawNumbers = drawNumbers;
	}


}
