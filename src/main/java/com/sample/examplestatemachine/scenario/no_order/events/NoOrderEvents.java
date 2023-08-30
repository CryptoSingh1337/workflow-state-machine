package com.sample.examplestatemachine.scenario.no_order.events;

import com.sample.examplestatemachine.generic.Event;

/**
 * @author Saransh Kumar
 */

public enum NoOrderEvents implements Event {

    USER_ID,
    ID_ACTIVE,
    ID_INACTIVE,
    ID_BLOCK,
    LOGIN_ZONE_AMONG_TAGGED_ZONE,
    LOGIN_ZONE_NOT_AMONG_TAGGED_ZONE,
    REST_OF_INDIA,
    BANGALORE,
    MISSED_ORDER,
    STALE_LOCATION,
    MDI_CASE,
    BOTH_DONE,
    NO_FORM,
    NO_SECURITY_DEPOSIT,
    NOT_DONE,
    SELF_CANCELLATION_MORE_OR_EQUAL_TO_2,
    SELF_CANCELLATION_LESS_THAN_2_AND_BANGALORE,
    SELF_CANCELLATION_LESS_THAN_2_AND_NOT_BANGALORE,
    ERROR;
}
