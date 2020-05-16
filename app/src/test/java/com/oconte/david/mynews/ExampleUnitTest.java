package com.oconte.david.mynews;

import com.oconte.david.mynews.Utils.ConfigureDate;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testCompareDate() {
        assertTrue(ConfigureDate.compareDate("01/05/2020", "16/05/2020"));
        assertTrue(ConfigureDate.compareDate("", "16/05/2020"));
        //assertFalse(ConfigureDate.compareDate("19/05/2020", "16/05/2020"));
    }
}