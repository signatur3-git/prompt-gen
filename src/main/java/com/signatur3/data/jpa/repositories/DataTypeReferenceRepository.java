package com.signatur3.data.jpa.repositories;

import com.signatur3.data.jpa.entities.DataTypeReferenceEntity;
import com.signatur3.data.jpa.entities.PromptSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataTypeReferenceRepository extends JpaRepository<DataTypeReferenceEntity, Long> {
    List<DataTypeReferenceEntity> findAllByPromptSectionOrderByIndexAsc(
            PromptSectionEntity promptSection);
}
