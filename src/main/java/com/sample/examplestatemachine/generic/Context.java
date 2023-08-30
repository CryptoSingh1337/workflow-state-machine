package com.sample.examplestatemachine.generic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Saransh Kumar
 */

public final class Context {

    private final Map<String, Object> ctx;

    private Context() {
        this.ctx = new HashMap<>();
    }

    public static Context createNewContext() {
        return new Context();
    }

    public boolean contains(String key) {
        return this.ctx.containsKey(key);
    }

    public boolean put(String key, Object object) {
        if (!this.ctx.containsKey(key) || "nextEvent".equals(key)) {
            this.ctx.put(key, object);
            return true;
        }
        return false;
    }

    public boolean remove(String key) {
        if (!this.ctx.containsKey(key)) {
            return false;
        }
        this.ctx.remove(key);
        return true;
    }

    public Object get(String key) {
        if (contains(key)) {
            return this.ctx.get(key);
        }
        return null;
    }

    public void clear() {
        this.ctx.clear();
    }

    public Map<String, Object> getCtx() {
        return Collections.unmodifiableMap(ctx);
    }

    @Override
    public String toString() {
        return this.ctx.toString();
    }
}
