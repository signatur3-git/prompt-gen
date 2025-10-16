package com.signatur3.data.model;

import com.signatur3.data.jpa.entities.NamespaceEntity;

public record Namespace(String id, String name) {

    public static Namespace of(NamespaceEntity entity) {
        return new Namespace(entity.getId().toString(), entity.getName());
    }
}
