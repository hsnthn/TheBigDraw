package com.lottery.bigDraw;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;

public class TheBigDrawTest extends TestCase {

    @Test
    public void testAddCoupon() throws Exception {
        TheBigDraw theBigDraw=Mockito.mock(TheBigDraw.class);
        theBigDraw.addCoupon();
        Mockito.doThrow(new Exception()).when(theBigDraw).addCoupon();
    }

    @Test
    public void testAddCouponNumber() throws Exception {
    	 TheBigDraw theBigDraw=Mockito.mock(TheBigDraw.class);
         theBigDraw.addCoupon();
         Mockito.doThrow(new Exception()).when(theBigDraw).addCouponNumber();
    }

}