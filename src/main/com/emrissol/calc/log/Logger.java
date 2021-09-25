package com.emrissol.calc.log;

import lombok.Setter;

public class Logger {

    private static final int LEVEL_INFO = 1;
    private static final int LEVEL_ERROR = 3;

    @Setter
    private boolean active = true;

    private Class<?> aClass;

    @Setter
    private int level = LEVEL_INFO;

    @Setter
    private String messagePrefix;

    public Logger(Class<?> aClass) {
        this.aClass = aClass;
        this.messagePrefix = "";
    }

    public Logger(Class<?> aClass, String messagePrefix) {
        this.aClass = aClass;
        this.messagePrefix = messagePrefix;
    }

    private static String getLogData(Class<?> aClass, String prefix, String message) {
        return "[".concat(aClass.getSimpleName()).concat("]\t\t").concat(prefix).concat(message);
    }

    private String getLogData(String message) {
        return getLogData(aClass, getPrefix(), message);
    }

    public String getPrefix() {
        return messagePrefix.isEmpty() ? "" : "[".concat(this.messagePrefix).concat("]\t");
    }

    public void log(String message) {
        if (!active) return;
        System.out.println(getLogData(message));
    }

    public void logError(String message) {
        if (!active) return;
        System.err.println(getLogData(message));
    }

    public static void log(Class<?> aClass, String message) {
        System.out.println(getLogData(aClass, "", message));
    }
}
