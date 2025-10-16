package com.signatur3.data.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "rulebook")
@Getter
public class RulebookEntity {

    @SuppressWarnings("unused" /* Managed by JPA */)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "namespace_id",
            foreignKey = @ForeignKey(name = "fk_rulebook_namespace")
    )
    NamespaceEntity namespace;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false, name = "entrysections")
    private String entrySections;

    @SuppressWarnings("unused" /* Used by JPA */)
    protected RulebookEntity() {
    }

    public RulebookEntity(NamespaceEntity namespace, String name, String entrySections) {
        this.namespace = namespace;
        this.name = name;
        this.entrySections = entrySections;
    }
}
