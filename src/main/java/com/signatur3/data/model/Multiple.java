package com.signatur3.data.model;

import com.signatur3.data.jpa.entities.SeparatorSetEntity;
import com.signatur3.utils.MultipleUtils;

public record Multiple(String namespace, Integer min, Integer max,
                       String separator, String separatorLast, String separatorTwoElements) {
    public static Multiple of(SeparatorSetEntity entity, Integer min, Integer max) {
        if (entity == null) {
            return MultipleUtils.getOnceInstance();
        }
        return new Multiple(entity.getNamespace().getName(), min, max, entity.getSeparator(),
                entity.getSeparatorLast(), entity.getSeparatorTwoElements());
    }
}
