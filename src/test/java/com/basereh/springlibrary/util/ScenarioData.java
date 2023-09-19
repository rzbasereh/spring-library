package com.basereh.springlibrary.util;

import io.cucumber.spring.ScenarioScope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ScenarioScope
public class ScenarioData {
    private final Map<String, Object> data = new HashMap<>();

    public void add(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public List<Object> getAll() {
        return data.values().stream().toList();
    }
}
