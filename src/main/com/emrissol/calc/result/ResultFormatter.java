package com.emrissol.calc.result;

public class ResultFormatter {

    private float fractionPrecision = 0.000001F;
    private String format = "%.5f";

    public ResultFormatter() {
    }

    public ResultFormatter(float fractionPrecision) {
        this.fractionPrecision = fractionPrecision;
    }

    public ResultFormatter(float fractionPrecision, String format) {
        this.fractionPrecision = fractionPrecision;
        this.format = format;
    }

    public String format(Double number) {
        String longValue = String.valueOf(number.longValue());
        if ( hasFractional(number)) {
            boolean hasExponent = number.toString().contains("E");
            if (number.longValue() < Long.MAX_VALUE && hasExponent) {
                return longValue;
            }
            else {
                return hasExponent ? number.toString() : String.format(format, number);
            }
        }
        return longValue;
    }

    public boolean hasFractional(double number) {
        return Math.abs(number - ((int) number)) > fractionPrecision;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public float getFractionPrecision() {
        return fractionPrecision;
    }

}
