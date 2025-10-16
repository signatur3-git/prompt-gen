package com.signatur3.data.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "promptgen_namespace")
@Getter
public class NamespaceEntity {

    @SuppressWarnings("unused" /* Managed by JPA */)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String name;

    protected NamespaceEntity() {
    }

    public NamespaceEntity(String name) {
        this.name = name;
    }
}
