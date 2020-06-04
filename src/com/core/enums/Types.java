package com.core.enums;

public enum Types {

    JS_LET("let"), JS_VAR("var"), JS_CONST("const"), JS_FUNCTION("function"), JS_CLASS("class"),

    JS_NUMBER("number"), JS_BOOL("boolean"), JS_STRING("string"), JS_OBJECT("object"), JS_ARRAY("array");

    private final String type;

    Types(String t) {
        type = t;
    }

    public String getType() {
        return type;
    }

}