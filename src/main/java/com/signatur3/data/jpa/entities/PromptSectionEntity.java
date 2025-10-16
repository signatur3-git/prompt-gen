package com.signatur3.data.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "promptsection")
@Getter
public class PromptSectionEntity {

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
    private String name;

    @Setter
    @Column(nullable = false)
    private String template;

    @SuppressWarnings("unused" /* Used by JPA */)
    protected PromptSectionEntity() {
    }

    public PromptSectionEntity(NamespaceEntity namespace, String name, String template) {
        this.namespace = namespace;
        this.name = name;
        this.template = template;
    }
}
