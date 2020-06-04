package com.core.interpreter;

import java.io.IOException;

import com.core.config.Setup;

public class Engine {
    public Engine(Setup setup) {
        try {
            new Reader(setup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}