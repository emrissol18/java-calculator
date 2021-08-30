package com.emrissol.dev.log;

import lombok.Setter;

public class Logger {

    private Class<?> aClass;

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

    public void log(String message) {
        String prefix = messagePrefix.isEmpty()
                ? ""
                : "[".concat(this.messagePrefix).concat("]\t");
        System.out.println("[".concat(aClass.getSimpleName()).concat("]\t\t").concat(prefix).concat(message));
    }

}
