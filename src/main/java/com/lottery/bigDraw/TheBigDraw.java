package com.lottery.bigDraw;

import com.lottery.bigDraw.entity.Coupon;
import com.lottery.bigDraw.entity.Result;
import com.lottery.bigDraw.gameRules.GameEngine;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "theBigDraw", eager = true)
@ViewScoped
public class TheBigDraw implements Serializable{

	
	 private List<Coupon> couponList=new ArrayList<Coupon>();
	 private List<Result> resultList=new ArrayList<Result>();
	 private Coupon coupon;
	 private Integer couponNumber;
	 private Date couponDate;
	 private String username;
	 private Boolean isfilledCoupon=false;
	 private List<String> playerNumbers=new ArrayList<String>();

	 public void addCoupon() throws Exception {
		 coupon=new Coupon();
		 try {
			 coupon.setDate(couponDate);
			 coupon.setUsername(username);
			 coupon.setPlayerNumbers(playerNumbers);
			 couponList.add(coupon);
			 initialize();
		 }catch (Exception e){
			 throw e;
		 }

	 }
	 
	 public void addCouponNumber() throws Exception{
		 try {
			 if(!isfilledCoupon){
				 playerNumbers.add(couponNumber.toString());
			 }
			 if(playerNumbers.size()==6){
				 isfilledCoupon=true;
			 }
			 couponNumber=null; //init
		} catch (Exception e) {
			throw e;
		}
	 }
	 
	 public void initialize(){
		 coupon=null;
		 username=null;
		 couponDate=null;
		 playerNumbers=new ArrayList<String>();
		 isfilledCoupon=false;
	 }
	 
	 
	 public void calculateWinnersAndResults() throws Exception {
		 GameEngine gameEngine=new GameEngine();
		 try {
			 resultList=gameEngine.run(couponList);
		 } catch (Exception e) {
			 throw e;
		 }
	 }
	 
	 
	 //Getter and Setter
	public List<Coupon> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}
	public List<Result> getResultList() {
		return resultList;
	}
	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public List<String> getPlayerNumbers() {
		return playerNumbers;
	}

	public void setPlayerNumbers(List<String> playerNumbers) {
		this.playerNumbers = playerNumbers;
	}

	public Integer getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(Integer couponNumber) {
		this.couponNumber = couponNumber;
	}

	public Date getCouponDate() {
		return couponDate;
	}

	public void setCouponDate(Date couponDate) {
		this.couponDate = couponDate;
	}

	public Boolean getIsfilledCoupon() {
		return isfilledCoupon;
	}

	public void setIsfilledCoupon(Boolean isfilledCoupon) {
		this.isfilledCoupon = isfilledCoupon;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}