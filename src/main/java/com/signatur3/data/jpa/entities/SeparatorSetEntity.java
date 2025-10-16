package com.signatur3.data.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "separatorset")
@Getter
public class SeparatorSetEntity {

    @SuppressWarnings("unused" /* Managed by JPA */)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "namespace_id",
            foreignKey = @ForeignKey(name = "fk_promptsection_namespace")
    )
    NamespaceEntity namespace;

    @Setter
    @Column(nullable = false)
    private String separator;

    @Setter
    private String separatorLast;

    @Setter
    private String separatorTwoElements;

    @SuppressWarnings("unused" /* Used by JPA */)
    protected SeparatorSetEntity() {
    }

    public SeparatorSetEntity(NamespaceEntity namespace, String separator) {
        this.namespace = namespace;
        this.separator = separator;
    }
}
