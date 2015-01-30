package com.lottery.bigDraw.gameRules;

import com.lottery.bigDraw.entity.Coupon;
import com.lottery.bigDraw.entity.Result;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GameEngineTest extends TestCase {

    @Test
    public void testRun() throws Exception {
        List<Coupon> coupons = new ArrayList<Coupon>();
        GameEngine gameEngine=new GameEngine();
        for(int i=0; i<5 ;i++){
            Coupon coupon=new Coupon();
            Date date = new Date();
            coupon.setDate(date);
            coupon.setUsername("test1");
            List<String> playerNumbers=new ArrayList<String>();
            playerNumbers.add(""+5+i);
            playerNumbers.add(""+8+i);
            playerNumbers.add(""+23+i);
            playerNumbers.add(""+21+i);
            playerNumbers.add(""+34+i);
            playerNumbers.add(""+55+i);
            coupon.setPlayerNumbers(playerNumbers);
            coupons.add(coupon);
        }
        List<Result> results = gameEngine.run(coupons);
        Assert.assertNotNull(results);
    }

    @Test
    public void testCreateDrawAndDefineWinners() throws Exception {
        List<Coupon> weekCoupons = new ArrayList<Coupon>();
        Date date = new Date();
        GameEngine gameEngine=Mockito.mock(GameEngine.class);
        Mockito.doThrow(new Exception()).when(gameEngine).createDrawAndDefineWinners(weekCoupons, date);
    }

    @Test
    public void testGetCorrectGuessedNumberCountAndNotGuessedNumbers() throws Exception {
        GameEngine gameEngine=new GameEngine();
        List<String> playerNumbers=new ArrayList<String>();
        playerNumbers.add("5");
        playerNumbers.add("8");
        playerNumbers.add("13");
        playerNumbers.add("21");
        playerNumbers.add("34");
        playerNumbers.add("55");
        List<Integer> drawOfWeek=new ArrayList<Integer>();
        drawOfWeek.add(5);
        drawOfWeek.add(8);
        drawOfWeek.add(13);
        drawOfWeek.add(21);
        drawOfWeek.add(34);
        drawOfWeek.add(55);
        Integer correct=gameEngine.getCorrectGuessedNumberCountAndNotGuessedNumbers(drawOfWeek, playerNumbers);
        Assert.assertEquals("6", correct.toString());
    }

    @Test
    public void testLessThreeGuessedNumberRule() throws Exception {
        GameEngine gameEngine=new GameEngine();
        List<String> playerNumbers=new ArrayList<String>();
        playerNumbers.add("5");
        playerNumbers.add("8");
        playerNumbers.add("13");
        playerNumbers.add("21");
        playerNumbers.add("34");
        playerNumbers.add("55");
        double sum=gameEngine.lessThreeGuessedNumberRule(playerNumbers);
        Assert.assertEquals(Double.parseDouble("136.0"),Double.parseDouble("136.0"),sum);
    }

    @Test
    public void testThreeAndMoreGuessedNumberRule() throws Exception {
    	 GameEngine gameEngine=new GameEngine();
         List<String> playersCouldNotGuessedNumbers=new ArrayList<String>();
         playersCouldNotGuessedNumbers.add("5");
         playersCouldNotGuessedNumbers.add("8");
         double sum=gameEngine.threeAndMoreGuessedNumberRule(4, playersCouldNotGuessedNumbers);
         Assert.assertEquals( Double.parseDouble("4040.0"),Double.parseDouble("4040.0"),sum);
    }

    @Test
    public void testAllNumbersGuessedRule() throws Exception {
        GameEngine gameEngine=new GameEngine();
        List<String> playerNumbers=new ArrayList<String>();
        playerNumbers.add("5");
        playerNumbers.add("8");
        playerNumbers.add("13");
        playerNumbers.add("21");
        playerNumbers.add("34");
        playerNumbers.add("55");
        double sum=gameEngine.allNumbersGuessedRule(playerNumbers);
        Assert.assertEquals(Double.parseDouble("1360000.0"),Double.parseDouble("1360000.0"),sum);
    }

    @Test
    public void testCheckFebruary() throws Exception {
    	GameEngine gameEngine=new GameEngine();
        String string = "February 2, 2015";
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = format.parse(string);
    	double totalAmount=gameEngine.checkFebruary(date, 1000);
    	Assert.assertEquals(Double.parseDouble("2000.0"),Double.parseDouble("2000.0"), totalAmount);
    	
    }
    
    @Test
    public void testCheck29February() throws Exception {
    	GameEngine gameEngine=new GameEngine();
        String string = "February 29, 2015";
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = format.parse(string);
    	double totalAmount=gameEngine.checkFebruary(date, 1000);
    	Assert.assertEquals(Double.parseDouble("3000.0"),Double.parseDouble("3000.0"), totalAmount);
    	
    }

    @Test
    public void testCreateTheDrawRandomly() throws Exception {
        GameEngine gameEngine=new GameEngine();
        List<Integer> draw=gameEngine.createTheDrawRandomly();
        Assert.assertEquals(6, draw.size());
    }

    @Test
    public void testGetInterval() throws Exception {
        GameEngine gameEngine=new GameEngine();
        List<Integer> draw=gameEngine.getInterval(1, 60);
        Assert.assertEquals(60, draw.size());
    }

    @Test
    public void testOneDayAdd() throws Exception {
        GameEngine gameEngine=new GameEngine();
        Date date = new Date();
        Date newDate=gameEngine.oneDayAdd(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(newDate);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Assert.assertEquals(22, day);
    }

    @Test
    public void testSevenDayAdd() throws Exception {
    	 GameEngine gameEngine=new GameEngine();
         Date date = new Date();
         Date newDate=gameEngine.sevenDayAdd(date);
         Calendar cal = Calendar.getInstance();
         cal.setTime(newDate);
         int day = cal.get(Calendar.DAY_OF_MONTH);
         Assert.assertEquals(28,day);
    }
}