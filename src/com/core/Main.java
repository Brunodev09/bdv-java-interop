package com.core;

import com.core.config.Setup;
import com.core.interpreter.Engine;

public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine(new Setup("src/com/core/file.js", true));
    }
}
