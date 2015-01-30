package com.lottery.bigDraw.gameRules;

import com.lottery.bigDraw.entity.Coupon;
import com.lottery.bigDraw.entity.Result;

import java.io.Serializable;
import java.util.*;

/**
 * Created by kafein on 1/15/2015.
 */
public class GameEngine implements Serializable {


    private static int START=1;
    private static int END=60;
    private Date startOfTheDraws;
    private  List<Result> resultList=new ArrayList<Result>();
    private  List<String> playersCouldNotGuessedNumbers=new ArrayList<String>();
   

	private double totalAmount=0.0;
    
   

	public List<Result> run (List<Coupon> coupons) throws Exception {
        //group by mondays and create draws for every monday
        Collections.sort(coupons, new Comparator<Coupon>() {
            public int compare(Coupon coupon1, Coupon coupon2) {
                return coupon1.getDate().compareTo(coupon2.getDate());
            }
        });


        Date firstGameDate=coupons.get(0).getDate();
        Date lastGameDate=coupons.get(coupons.size()-1).getDate();
        while(firstGameDate.getDay()!=Calendar.MONDAY){
            //add 1 day
            firstGameDate=oneDayAdd(firstGameDate);
        }

        startOfTheDraws=firstGameDate;

        //process of all coupons
        while(startOfTheDraws.before(lastGameDate)){
            try {
                allCouponsProcess(coupons);
            } catch (Exception e) {
                throw e;
            }
            startOfTheDraws=sevenDayAdd(startOfTheDraws);
        }
        
        //last week process
        if(startOfTheDraws.after(lastGameDate)){
            try {
                allCouponsProcess(coupons);
            } catch (Exception e) {
                throw e;
            }
        }


        return resultList;
    }
    
	
	private void allCouponsProcess(List<Coupon> coupons) throws Exception {
    	List<Coupon> weekCoupons=new ArrayList<Coupon>();
        for (Iterator<Coupon> it = coupons.iterator(); it.hasNext(); ) {
        	Coupon coupon = it.next();
        	 if(coupon.getDate().before(startOfTheDraws)){
	            	weekCoupons.add(coupon);
	            	
	            	//remove processed coupons
	            	it.remove();
	            }
        }
        
        //define results for coupons of the week
        createDrawAndDefineWinners(weekCoupons, startOfTheDraws);
	}
	
	
    public void createDrawAndDefineWinners(List<Coupon> weekCoupons,Date drawDate) throws Exception {
    	try {
            //create draw  of the week randomly
            List<Integer> drawOfWeek=createTheDrawRandomly();
            List<String> drawOfWeekAsString=convertDrawList(drawOfWeek);


            //define weekly coupon's results
            for(Coupon coupon:weekCoupons){
                int correctGuessedNumber=getCorrectGuessedNumberCountAndNotGuessedNumbers(drawOfWeek, coupon.getPlayerNumbers());
                double prize=0;
                Result result=new Result();
                result.setCouponDate(coupon.getDate());
                result.setDrawDate(drawDate);
                result.setPlayerNumbers(coupon.getPlayerNumbers());
                result.setDrawNumbers(drawOfWeekAsString);
                result.setUsername(coupon.getUsername());

                //processing prize
                if(correctGuessedNumber<3){
                    prize=lessThreeGuessedNumberRule(coupon.getPlayerNumbers());
                }else if((correctGuessedNumber>2) && (correctGuessedNumber<6)){
                    prize=threeAndMoreGuessedNumberRule(correctGuessedNumber, playersCouldNotGuessedNumbers);
                }else if(correctGuessedNumber==6){
                    prize=allNumbersGuessedRule(coupon.getPlayerNumbers());
                }

                //February
                prize=checkFebruary(coupon.getDate(), prize);

                result.setWinnigPrize(prize);
                resultList.add(result);
            }
        }catch (Exception e){
            throw e;
        }

    }


    private List<String> convertDrawList(List<Integer> drawOfWeek){
    	 List<String> drawOfWeekAsString= new ArrayList<String>();
    	 for(Integer drawNumber:drawOfWeek){
    		 drawOfWeekAsString.add(drawNumber.toString());
    	 }
    	 return drawOfWeekAsString;
    }
    
    public int getCorrectGuessedNumberCountAndNotGuessedNumbers(List<Integer> drawOfWeek, List<String> playerNumbers){
        int corractGuess=0;
        for(Integer drawNumber:drawOfWeek){
            for(String playerNumber:playerNumbers){
                if(drawNumber.toString().equals(playerNumber)){
                    corractGuess++;
                }else{
                	if(playersCouldNotGuessedNumbers.indexOf(playerNumber)==-1){
                		playersCouldNotGuessedNumbers.add(playerNumber);
                	}
                }
            }
        }
        return corractGuess;
    }

    public double lessThreeGuessedNumberRule(List<String>  playersNumbers){
        int totalOfPlayerNumbers=0;
        for(String playerNumber:playersNumbers){
            totalOfPlayerNumbers=totalOfPlayerNumbers+Integer.parseInt(playerNumber);
        }
        return totalOfPlayerNumbers;
    }

    public double threeAndMoreGuessedNumberRule(int correctGuessedNumber, List<String>  playersCouldNotGuessedNumbers){
        int multiplexOfPlayerNumbers=1;
        for(String playerNumber:playersCouldNotGuessedNumbers){
            multiplexOfPlayerNumbers=multiplexOfPlayerNumbers*Integer.parseInt(playerNumber);
        }

        return 1000*correctGuessedNumber+multiplexOfPlayerNumbers;
    }


    public double allNumbersGuessedRule(List<String>  playersNumbers){
        int totalOfPlayerNumbers=0;
        for(String playerNumber:playersNumbers){
            totalOfPlayerNumbers=totalOfPlayerNumbers+Integer.parseInt(playerNumber);
        }

        return 10000*totalOfPlayerNumbers;
    }



    public double checkFebruary(Date date,double totalAmount){
        if(date.getMonth()== Calendar.FEBRUARY && date.getDay()==29){
            return totalAmount*3;
        }else if(date.getMonth()== Calendar.FEBRUARY){
            return totalAmount*2;
        }else{
            return totalAmount;
        }
    }



    public List<Integer> createTheDrawRandomly() {
        List<Integer> drawNumbers=getInterval(START, END);
        Collections.shuffle(drawNumbers);
        List<Integer> subItems = new ArrayList<Integer>(drawNumbers.subList(0, 6));
        return subItems;

    }

    public List<Integer> getInterval(int start,int finish){
        List<Integer> list=new ArrayList<Integer>();
        for(int i=start; i<=finish; i++){
            list.add(i);
        }

        return list;

    }

    public Date oneDayAdd(Date date){
    	 Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.add(Calendar.DATE, 1);
         return cal.getTime();
    }

    public Date sevenDayAdd(Date date){
   	 Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
   }


    //Getters and Setters
    public Date getStartOfTheDraws() {
        return startOfTheDraws;
    }

    public void setStartOfTheDraws(Date startOfTheDraws) {
        this.startOfTheDraws = startOfTheDraws;
    }


	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}


	public List<Result> getResultList() {
		return resultList;
	}
	
	 public double getTotalAmount() {
			return totalAmount;
		}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	 public List<String> getPlayersCouldNotGuessedNumbers() {
			return playersCouldNotGuessedNumbers;
		}

	public void setPlayersCouldNotGuessedNumbers(List<String> playersCouldNotGuessedNumbers) {
		this.playersCouldNotGuessedNumbers = playersCouldNotGuessedNumbers;
	}


}
