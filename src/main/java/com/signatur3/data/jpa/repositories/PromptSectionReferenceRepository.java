package com.signatur3.data.jpa.repositories;

import com.signatur3.data.jpa.entities.PromptSectionEntity;
import com.signatur3.data.jpa.entities.PromptSectionReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromptSectionReferenceRepository extends JpaRepository<PromptSectionReferenceEntity, Long> {
    List<PromptSectionReferenceEntity> findAllByPromptSectionOrderByIndexAsc(PromptSectionEntity promptSection);
}
