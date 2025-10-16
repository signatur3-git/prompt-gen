package com.signatur3.data.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "datatypereference")
@Getter
public class DataTypeReferenceEntity {

    @SuppressWarnings("unused" /* Managed by JPA */)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "promptsection_id",
            foreignKey = @ForeignKey(name = "fk_datatypereference_promptsection")
    )
    PromptSectionEntity promptSection;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "reference_id",
            foreignKey = @ForeignKey(name = "fk_datatypereference_reference")
    )
    DataTypeEntity reference;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "separatorset_id",
            foreignKey = @ForeignKey(name = "fk_datatypereference_separatorset")
    )
    SeparatorSetEntity separatorSet;

    @Setter
    private Integer index;

    @Setter
    private Integer min;

    @Setter
    private Integer max;

    @Setter
    private String name;

    @SuppressWarnings("unused" /* Used by JPA */)
    protected DataTypeReferenceEntity() {
    }

    public DataTypeReferenceEntity(PromptSectionEntity promptSection, DataTypeEntity reference, Integer index,
                                   String name) {
        this.promptSection = promptSection;
        this.reference = reference;
        this.index = index;
        this.name = name;
    }
}
