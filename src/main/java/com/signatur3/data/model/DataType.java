package com.signatur3.data.model;

import com.signatur3.data.jpa.entities.DataTypeEntity;

import java.util.List;

public record DataType(String id, String namespace, String name, List<DataValue> values) {
    public static DataType of(DataTypeEntity entity, List<DataValue> values) {
        return new DataType(entity.getId().toString(), entity.getNamespace().getName(),
                entity.getName(), values);
    }
}
