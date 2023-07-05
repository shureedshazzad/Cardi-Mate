package com.example.cardimate.Class;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.example.cardimate.R;

/**
 * This is java class for Unit Testing
 */
public class CardmodelTest {

    /**
     * This method checks whether the inserted systolic pressure will add "mmhg" after it as unit
     */
    @Test
    public void testGetFormattedSys() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setSystolicPressure("120");
        String formattedSys = cardmodel.getFormattedSys();
        assertEquals("120 mmHg", formattedSys);
    }

    /**
     * This method checks whether the inserted diastolic pressure will add "mmhg" after it as unit
     */
    @Test
    public void testGetFormattedDys() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setDiastolicPressure("80");
        String formattedDys = cardmodel.getFormattedDys();
        assertEquals("80 mmHg", formattedDys);
    }

    /**
     * This method checks whether the inserted systolic pressure will add "bpm" after it as unit
     */
    @Test
    public void testGetFormattedHeartRate() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setHeartRate("70");
        String formattedHeartRate = cardmodel.getFormattedHeartRate();
        assertEquals("70 bpm", formattedHeartRate);
    }

    /**
     * This method checks whether the inserted comment will return red color
     */
    @Test
    public void testGetBackColorWithHighComment() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setComment("high");
        int backColor = cardmodel.getBackColor();
        assertEquals(R.color.red, backColor);
    }


    /**
     * This method checks whether the inserted comment will return red color
     */
    @Test
    public void testGetBackColorWithLowComment() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setComment("low");
        int backColor = cardmodel.getBackColor();
        assertEquals(R.color.red, backColor);
    }


    /**
     * This method checks whether the inserted comment will return orange color
     */
    @Test
    public void testGetBackColorWithOtherComment() {

        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setComment("normal");
        int backColor = cardmodel.getBackColor();
        assertEquals(R.color.orange, backColor);
    }
}
