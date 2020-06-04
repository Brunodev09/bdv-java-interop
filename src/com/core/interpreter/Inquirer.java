package com.core.interpreter;

public class Inquirer {

    public static Boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static Boolean isBoolean(String s) {
        return s.trim().equals("true") || s.trim().equals("false");
    }

    public static Boolean isString(String s) {
        return (s.trim().startsWith("\'") && s.trim().endsWith("\'")) || (s.trim().startsWith("\"") && s.trim().endsWith("\""));
    }

    public static Boolean isArray(String s) {
        return s.trim().startsWith("[") && s.trim().endsWith("]");
    }

    public static Boolean isFunction(String s) {
        return s.contains("function") && s.endsWith("}");
    }

    public static Boolean isObject(String s) {
        return s.trim().startsWith("{") && s.trim().endsWith("}") && !Inquirer.isFunction(s);
    }

    public static Boolean isJsLet(String s) {
        return s.trim().equals("let");
    }

    public static Boolean isJsConst(String s) {
        return s.trim().equals("const");
    }

    public static Boolean isJsVar(String s) {
        return s.trim().equals("var");
    }

    public static Boolean isJsClass(String s) {
        return s.trim().equals("class");
    }

}