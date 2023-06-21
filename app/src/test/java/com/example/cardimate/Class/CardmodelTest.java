package com.example.cardimate.Class;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.example.cardimate.R;

public class CardmodelTest {

    @Test
    public void testGetFormattedSys() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setSystolicPressure("120");
        String formattedSys = cardmodel.getFormattedSys();
        assertEquals("120 mmHg", formattedSys);
    }

    @Test
    public void testGetFormattedDys() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setDiastolicPressure("80");
        String formattedDys = cardmodel.getFormattedDys();
        assertEquals("80 mmHg", formattedDys);
    }

    @Test
    public void testGetFormattedHeartRate() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setHeartRate("70");
        String formattedHeartRate = cardmodel.getFormattedHeartRate();
        assertEquals("70 bpm", formattedHeartRate);
    }

    @Test
    public void testGetBackColorWithHighComment() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setComment("high");
        int backColor = cardmodel.getBackColor();
        assertEquals(R.color.red, backColor);
    }

    @Test
    public void testGetBackColorWithLowComment() {
        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setComment("low");
        int backColor = cardmodel.getBackColor();
        assertEquals(R.color.red, backColor);
    }

    @Test
    public void testGetBackColorWithOtherComment() {

        Cardmodel cardmodel = new Cardmodel();
        cardmodel.setComment("normal");
        int backColor = cardmodel.getBackColor();
        assertEquals(R.color.orange, backColor);
    }
}
