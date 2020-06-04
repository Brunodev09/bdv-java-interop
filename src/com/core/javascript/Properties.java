package com.core.javascript;

import com.core.data.JsJavaObject;
import com.core.enums.Types;
import com.core.interpreter.Inquirer;

public class Properties {

    private int Id;
    private String name;
    private Boolean numeric;
    private Boolean bool;
    private Boolean string;
    private Boolean object;
    private boolean array;

    private String value;

    private Boolean isConst;
    private Boolean isLet;
    private Boolean isVar;
    private Boolean isFunction;
    private Boolean isClass;

    private Boolean _isValid = true;

    private JsJavaObject convertedJavaObject;

    public Properties(int Id) {
        this.Id = Id;
    }

    public Boolean getNumeric() {
        return this.numeric;
    };

    public Boolean getArray() {
        return this.array;
    }

    public Boolean getBoolean() {
        return this.bool;
    };

    public Boolean getString() {
        return this.string;
    };

    public Boolean getObject() {
        return this.object;
    };

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getValidity() {
        return this._isValid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        int flag = 0;

        this.numeric = Inquirer.isNumeric(this.value);
        this.bool = Inquirer.isBoolean(this.value);
        this.string = Inquirer.isString(this.value);
        this.object = Inquirer.isObject(this.value);
        this.array = Inquirer.isArray(this.value);

        if (this.numeric) flag++;
        if (this.bool) flag++;
        if (this.string) flag++;
        if (this.object) flag++;
        if (this.array) flag++;

        if (flag == 0 || flag > 1) this._isValid = false;
    };

    private void setPreDefinedType(String type) {
        int flag = 0;

        this.isLet = Inquirer.isJsLet(type);
        this.isConst = Inquirer.isJsConst(type);
        this.isVar = Inquirer.isJsVar(type);
        this.isFunction = Inquirer.isFunction(type);
        this.isClass = Inquirer.isJsClass(type);

        if (this.isLet) flag++;
        if (this.isConst) flag++;
        if (this.isVar) flag++;
        if (this.isFunction) flag++;
        if (this.isClass) flag++;

        if (flag == 0 || flag > 1) this._isValid = false;
    }

    public void setEncodedObject(String lvalue, String rvalue) {
        String preType = lvalue.split(" ")[0];
        String objName = lvalue.split(" ")[1];

        this.setName(objName);
        this.setPreDefinedType(preType);
        this.setValue(rvalue);
    }

    public void printObjectOverview() {
        Types declaration = null;
        Types type = null;

        if (this.isLet) declaration = Types.JS_CLASS;
        if (this.isConst) declaration = Types.JS_CONST;
        if (this.isLet) declaration = Types.JS_LET;
        if (this.isFunction) declaration = Types.JS_FUNCTION;
        if (this.isVar) declaration = Types.JS_VAR;

        if (this.numeric) type = Types.JS_NUMBER;
        if (this.bool) type = Types.JS_BOOL;
        if (this.string) type = Types.JS_STRING;
        if (this.object) type = Types.JS_OBJECT;

        if (declaration != null && type != null) {
            System.out.println("| ID | " + this.Id + " | DECL | " + declaration.getType() + " | NAME | " + this.name + " | TYPE | " + type.getType() + " | VALID | " + this._isValid);
            return;
        }
        System.out.println(" | DECL |  _INVALID_JS_DECLARATION_ | TYPE | _INVALID_JS_OBJECT_TYPE_");
    }

}