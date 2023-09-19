package com.basereh.springlibrary.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenarioData {
    private final Map<String, Object> data = new HashMap<>();

    public void add(String key, Object value) {
        data.put(key, value);
    }

    public <T> T get(String key) {
        return (T) data.get(key);
    }

    public <T> List<T> getAll() {
        return (List<T>) data.values().stream().toList();
    }

    public <T> List<T> getAll(Class<T> type) {
        return data.values().stream()
                .filter(o -> type.isAssignableFrom(o.getClass()))
                .map(o -> (T) o)
                .toList();
    }
}
