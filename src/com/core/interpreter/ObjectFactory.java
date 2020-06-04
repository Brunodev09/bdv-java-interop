package com.core.interpreter;

import com.core.javascript.JsObject;
import com.core.data.JsJavaObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ObjectFactory {

    public static JsJavaObject create(JsObject js) {
        JsJavaObject object = new JsJavaObject();

        if (js.properties.getNumeric()) {
            object._m.put(js.properties.getName(), Double.parseDouble(js.properties.getValue().trim()));
        } else if (js.properties.getBoolean()) {
            object._m.put(js.properties.getName(), Boolean.parseBoolean(js.properties.getValue().trim()));
        } else if (js.properties.getString()) {
            object._m.put(js.properties.getName(), js.properties.getValue());
        } else if (js.properties.getArray()) {

        } else if (js.properties.getObject()) {
            String value = js.properties.getValue().trim();
            JsJavaObject emptyObject = new JsJavaObject();
            HashMap<String, Integer> properties = new HashMap<>();
            int depth = 0;

            object = ObjectFactory.parseDeep(value, emptyObject, properties, depth);
            // object._m.put(js.properties.getName(), js.properties.getValue());
        }

        return object;

    }

    public static JsJavaObject parseDeep(String object, JsJavaObject apply, HashMap<String, Integer> props, int depth) {
        depth++;
        String strippedObject = object.substring(1, object.length() - 1);
        String[] possibleProperty = strippedObject.split(":");

        // Empty object
        if (possibleProperty[0].isEmpty())
            return apply;

        strippedObject = "";
        int counter = 0;
        for (String each : possibleProperty) {
            if (each.split(",").length > 1) {
                strippedObject += "__assign__" + each.split(",")[0].trim() + ",";
                strippedObject += each.split(",")[1].trim();
            } else {
                if (counter != 0)
                    strippedObject += "__assign__" + each.trim();
                else
                    strippedObject += each.trim();
            }
            counter++;
        }

        String[] properties = strippedObject.split(",");

        for (String property : properties) {

            String key = property.split("__assign__")[0];
            String value = property.split("__assign__")[1];

            if (!Inquirer.isObject(value) && !Inquirer.isArray(value)) {

                if (Inquirer.isNumeric(value)) {
                    apply._m.put(key, Double.parseDouble(value));
                }
                if (Inquirer.isBoolean(value)) {
                    apply._m.put(key, Boolean.parseBoolean(value));
                }
                if (Inquirer.isString(value)) {
                    String cleanVal = "";
                    if (value.contains("\""))
                        cleanVal = value.replace("\"", "");
                    else
                        cleanVal = value.replace("\'", "");
                    apply._m.put(key, cleanVal);
                }
            }

            else if (Inquirer.isObject(value)) {
                JsJavaObject nested = ObjectFactory.parseDeep(object, new JsJavaObject(), props, depth);
                apply._m.put(key, nested);
            }

            else if (Inquirer.isArray(value)) {
                // implement array parsing
            }
        }
        return apply;
    }

}