package com.emrissol.calc.result;

import org.junit.Assert;
import org.junit.Test;

public class ResultFormatterTest {

    @Test
    public void testFloatPointFormat(){
        Double v = 1.6666666666666667;
        ResultFormatter formatter = new ResultFormatter();

        System.out.println(formatter.format(v));
    }


    @Test
    public void testFormat() {
        ResultFormatter formatter = new ResultFormatter();

        Double raw1 = 1.1241224;
        Double raw2 = 1.000000d;
        Double raw3 = 1.12412e24;
        Double raw4 = Math.pow(2, 32);

        Assert.assertEquals(raw1.toString(), formatter.format(raw1));
        Assert.assertEquals("1", formatter.format(raw2));
        Assert.assertEquals(raw3.toString(), formatter.format(raw3));
        Assert.assertEquals("4294967296", formatter.format(raw4));

//        System.out.println("formatter.format(raw1) = " + formatter.format(raw1));
//        System.out.println("formatter.format(raw2) = " + formatter.format(raw2));
//        System.out.println("formatter.format(raw3) = " + formatter.format(raw3));
//        System.out.println("formatter.format(raw4) = " + formatter.format(raw4));

    }

}
