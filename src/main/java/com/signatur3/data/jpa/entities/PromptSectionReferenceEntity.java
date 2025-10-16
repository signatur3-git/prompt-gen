package com.signatur3.data.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "promptsectionreference")
@Getter
public class PromptSectionReferenceEntity {

    @SuppressWarnings("unused" /* Managed by JPA */)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "promptsection_id",
            foreignKey = @ForeignKey(name = "fk_promptsectionreference_promptsection")
    )
    PromptSectionEntity promptSection;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "reference_id",
            foreignKey = @ForeignKey(name = "fk_promptsectionreference_reference")
    )
    PromptSectionEntity reference;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "separatorset_id",
            foreignKey = @ForeignKey(name = "fk_datatypereference_separatorset")
    )
    SeparatorSetEntity separatorSet;

    @Setter
    @Column(nullable = false)
    private Integer index;

    @Setter
    private Integer min;

    @Setter
    private Integer max;

    @Setter
    private String name;

    @SuppressWarnings("unused" /* Used by JPA */)
    protected PromptSectionReferenceEntity() {
    }

    public PromptSectionReferenceEntity(PromptSectionEntity promptSection, PromptSectionEntity reference,
                                        Integer index, String name) {
        this.promptSection = promptSection;
        this.reference = reference;
        this.index = index;
        this.name = name;
    }
}
