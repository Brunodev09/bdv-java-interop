package com.core.javascript;

public class JsObject {

    private static int _GLOBAL_ID_ = 0;
    public Properties properties;

    public JsObject(String lvalue, String rvalue) {
        _GLOBAL_ID_++;
        properties = new Properties(_GLOBAL_ID_);
        properties.setEncodedObject(lvalue, rvalue);
    }
    
}