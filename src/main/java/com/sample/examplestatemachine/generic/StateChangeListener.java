package com.sample.examplestatemachine.generic;

import java.util.List;

/**
 * @author Saransh Kumar
 */

public interface StateChangeListener {

    void onStageChange(String id, Event event, List<State> transition, Context context);
}
