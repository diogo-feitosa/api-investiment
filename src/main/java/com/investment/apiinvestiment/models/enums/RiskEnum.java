package com.investment.apiinvestiment.models.enums;

import java.util.Arrays;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public enum RiskEnum {
    BAIXO(0, "BAIXO"),
    MEDIO(1, "MEDIO"),
    ALTO(2, "ALTO");

    private int  value;

    private String initials;

    private RiskEnum(int value, String initials) {
        this.value = value;
        this.initials = initials;
    }

    public int getValue() {
        return value;
    }

    public String getInitials() {
        return initials;
    }

    private static ImmutableMap<Integer, RiskEnum> reverseLookupValue =
        Maps.uniqueIndex(Arrays.asList(RiskEnum.values()), RiskEnum::getValue);

    public static RiskEnum fromValue(final int value) {
        return reverseLookupValue.getOrDefault(value, BAIXO);
    }

    private static ImmutableMap<String, RiskEnum> reverseLookupInitials =
        Maps.uniqueIndex(Arrays.asList(RiskEnum.values()), RiskEnum::getInitials);

    public static RiskEnum fromInitials(final String initials) {
        return reverseLookupInitials.getOrDefault(initials, BAIXO);
    }

    public static RiskEnum existFromInitials(final String initials) {
        return reverseLookupInitials.get(initials);
    }
}
