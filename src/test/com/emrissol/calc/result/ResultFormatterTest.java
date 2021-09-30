package com.emrissol.calc.result;

import org.junit.Assert;
import org.junit.Test;

public class ResultFormatterTest {

    private ResultFormatter formatter = new ResultFormatter();
    @Test
    public void testFloatPointFormat1(){
        Double v1 = 1.6666666666666667;
        Double v2 = 3.027243733E9;
        Double v3 = 1.0100;
        Double v4 = 1.0000001;
        Assert.assertEquals("1.6666666666666667", formatter.format(v1));
        Assert.assertEquals("3027243733", formatter.format(v2));
        Assert.assertEquals("1.01", formatter.format(v3));
        Assert.assertEquals("1", formatter.format(v4));
    }

}
