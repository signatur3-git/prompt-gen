package com.signatur3.data.model;

import com.signatur3.data.jpa.entities.RulebookEntity;

import java.util.Arrays;
import java.util.List;

public record Rulebook(String id, String namespace, String name, List<String> entrySections) {
    public static Rulebook of(RulebookEntity entity) {
        return new Rulebook(entity.getId().toString(), entity.getNamespace().getName(), entity.getName(),
                Arrays.asList(entity.getEntrySections().split(",")));
    }
}
