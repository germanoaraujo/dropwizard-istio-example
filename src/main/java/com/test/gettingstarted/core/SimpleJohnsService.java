package com.test.gettingstarted.core;

public class SimpleJohnsService implements JohnsService {

    public JohnsModel getInfo() {
        return new JohnsModel("Red", 42);
    }
}