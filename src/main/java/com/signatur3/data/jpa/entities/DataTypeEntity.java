package com.signatur3.data.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "datatype")
@Getter
public class DataTypeEntity {

    @SuppressWarnings("unused" /* Managed by JPA */)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "namespace_id",
            foreignKey = @ForeignKey(name = "fk_datatype_namespace")
    )
    NamespaceEntity namespace;

    @Setter
    @Column(nullable = false)
    private String name;

    @SuppressWarnings("unused" /* Used by JPA */)
    protected DataTypeEntity() {
    }

    public DataTypeEntity(NamespaceEntity namespace, String name) {
        this.namespace = namespace;
        this.name = name;
    }
}
