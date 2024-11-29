package com.erebelo.springmysqldemo.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BusinessConstant {

    public static final String HEALTH_CHECK_PATH = "/health-check";
    public static final String ADVISORS_PATH = "/advisors";
    public static final String BROKERS_PATH = "/brokers";
    public static final String BROKER_TYPES_PATH = BROKERS_PATH + "/types";

}
