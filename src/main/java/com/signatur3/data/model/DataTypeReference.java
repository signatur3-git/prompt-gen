package com.signatur3.data.model;

import com.signatur3.data.jpa.entities.DataTypeReferenceEntity;

public record DataTypeReference(String id, String name, String type, Multiple multiple) {
    public static DataTypeReference of(DataTypeReferenceEntity entity) {
        return new DataTypeReference(entity.getId().toString(),
                entity.getName(),
                entity.getReference().getNamespace().getName() + "::" + entity.getReference().getName(),
                Multiple.of(entity.getSeparatorSet(), entity.getMin(), entity.getMax()));
    }
}
