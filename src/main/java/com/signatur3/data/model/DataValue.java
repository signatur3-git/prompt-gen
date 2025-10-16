package com.signatur3.data.model;

import com.signatur3.data.jpa.entities.DataValueEntity;

public record DataValue(String id, String value) {
    public static DataValue of(DataValueEntity entity) {
        return new DataValue(entity.getId().toString(), entity.getValue());
    }
}
