package com.core.config;

public class Setup {

    private Boolean _debug;
    private String _location;

    public Setup(String location, Boolean debug) {
        this._location = location;
        this._debug = debug;
    }

    public String getLocation() {
        return _location;
    }

    public Boolean getDebugMode() {
        return this._debug;
    }
    
}