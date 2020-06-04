package com.core.interpreter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.core.config.Setup;
import com.core.data.JsJavaObject;
import com.core.javascript.JsObject;
import com.core.data.JsJavaObject;


import java.io.IOException;

public class Reader {
    private Path path;
    private String str;
    private String[] expressions;
    private int _line = 1;
    private ArrayList<JsObject> _objectList = new ArrayList<JsObject>();

    public Reader(Setup setup) throws IOException {
        try {

            path = Paths.get(setup.getLocation());
            str = Files.readString(path);
            expressions = str.split(";");

            for (String expression : expressions) {
                String lvalue = expression.split("=")[0];
                String rvalue = expression.split("=")[1];
                JsObject obj = new JsObject(lvalue, rvalue);
                _objectList.add(obj);
                this._line++;
                if (setup.getDebugMode()) obj.properties.printObjectOverview();
            }

            for (JsObject js : _objectList) {

                if (js.properties.getValidity()) {
                    JsJavaObject object = ObjectFactory.create(js);
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}