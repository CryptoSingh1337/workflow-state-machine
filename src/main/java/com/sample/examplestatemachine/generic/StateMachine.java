package com.sample.examplestatemachine.generic;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Saransh Kumar
 */

@Getter
@Setter
public abstract class StateMachine {

    protected String ID;
    protected final Context context = Context.createNewContext();
    protected final Map<String, Event> events = new HashMap<>();
    protected final Map<String, State> states = new HashMap<>();
    protected final Map<Event, List<State>> transitions = new HashMap<>();
    protected StateChangeListener stateChangeListener;
    protected State currentState;

    public StateMachine(String ID) {
        this.ID = ID;
    }

    public abstract void config();

    public abstract State getCurrentState();

    public abstract void start();

    public abstract void stop();

    public abstract void sendEvent(Event event);

    public boolean put(String key, Object object) {
        return this.context.put(key, object);
    }

    public boolean remove(String key) {
        return this.context.remove(key);
    }

    public Map<String, Object> getContext() {
        return this.context.getCtx();
    }

    public void printContext() {
        System.out.println(this.context);
    }
}
