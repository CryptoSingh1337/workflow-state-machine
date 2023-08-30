package com.sample.examplestatemachine.generic;

import lombok.Getter;

/**
 * @author Saransh Kumar
 */

@Getter
public abstract class State {

    private final String ID;
    private boolean isFinal;
    private boolean requireUserInput;

    public State(String id) {
        this.ID = id;
    }

    public abstract void action(Context context);

    public abstract State transition(Context context);

    public abstract boolean guard(Context context);

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean getRequireUserInput() {
        return requireUserInput;
    }

    public void setRequireUserInput(boolean requireUserInput) {
        this.requireUserInput = requireUserInput;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        State state = (State) object;
        return ID.equals(state.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public String toString() {
        return "State{" +
                "ID='" + ID + '\'' +
                '}';
    }
}
