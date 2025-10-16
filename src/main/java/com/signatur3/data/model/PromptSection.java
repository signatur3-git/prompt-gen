package com.signatur3.data.model;

import com.signatur3.data.jpa.entities.PromptSectionEntity;

import java.util.List;

public record PromptSection(String id, String namespace, String name, String template,
                            List<PromptSectionReference> sectionReferences,
                            List<DataTypeReference> dataTypeReferences) {
    public static PromptSection of(PromptSectionEntity entity,
                                   List<PromptSectionReference> sectionReferences,
                                   List<DataTypeReference> dataTypeReferences) {
        return new PromptSection(entity.getId().toString(), entity.getNamespace().getName(), entity.getName(),
                entity.getTemplate(), sectionReferences, dataTypeReferences);
    }
}
