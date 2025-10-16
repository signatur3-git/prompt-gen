package com.signatur3.data.model;

import com.signatur3.data.jpa.entities.PromptSectionReferenceEntity;

public record PromptSectionReference(String name, String type, Multiple multiple) {
    public static PromptSectionReference of(PromptSectionReferenceEntity entity) {
        return new PromptSectionReference(
                entity.getName(),
                entity.getReference().getNamespace().getName() + "::" +
                        entity.getReference().getName(),
                Multiple.of(entity.getSeparatorSet(), entity.getMin(), entity.getMax()));
    }
}
