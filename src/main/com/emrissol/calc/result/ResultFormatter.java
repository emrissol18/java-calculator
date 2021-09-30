package com.emrissol.calc.result;

/**
 * This class could format result double value in shorter fashion.<br/>
 * <i>Note:</i> setting fractionPrecision value to -1 will prevent formatting.
 */
public class ResultFormatter {

    private float fractionPrecision = 0.000009F;

    public ResultFormatter() {
    }

    public ResultFormatter(float fractionPrecision) {
        this.fractionPrecision = fractionPrecision;
    }

    public ResultFormatter(float fractionPrecision, String format) {
        this.fractionPrecision = fractionPrecision;
    }

    /**
     * Format double value by trancating digits length after point
     * to specified <code>fractionPrecision</code> if has fractions or format to long.
     *
     * @param number double value
     * @return  formatted number as string
     */
    public String format(Double number) {
        String longValue = String.valueOf(number.longValue());
        if ( hasFractions(number)) {
            boolean hasExponent = number.toString().contains("E");
            if (number.longValue() < Long.MAX_VALUE && hasExponent) {
                return longValue;
            }
            else {
//                return hasExponent ? number.toString() : String.format(format, number);
//                if ( ! hasExponent) {
//                    String formatted = String.format(format, number);
//                    int length = formatted.length();
//                    System.out.println("length = " + length);
//                    char lastchar = formatted.charAt(length-1);
//                    System.out.println("lastchar = " + lastchar);
//                    while (lastchar == '0' && lastchar != '.') {
//                        formatted = HtmlStringUtil.removeLastChar(formatted);
//                        lastchar = formatted.charAt(--length);
//                    }
//                    return formatted.substring(0, formatted.length() - length-1);
//                }
                return number.toString();
            }
        }
        return longValue;
    }

    /**
     * Determines if double value has digits after point.
     * <code>fractionPrecision</code> threshold value will be used to determine if value has fractions.<br/>
     * e.g. if <b>fractionPrecision</b> = 0.001<br/>
     * then 2.02 - has fractions<br/>
     * and 2.0 - has no fractions<br/>
     * but 2.00001 - also has no fractions.
     *
     * @param number double value
     * @return true if has fractions, otherwise - false
     */
    public boolean hasFractions(Double number) {
        if (fractionPrecision == -1) {
            return number != 0.0;
        }
        return Math.abs(number - number.intValue()) >= fractionPrecision;
    }

    public float getFractionPrecision() {
        return fractionPrecision;
    }

}
