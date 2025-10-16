package com.signatur3.data.jpa.repositories;

import com.signatur3.data.jpa.entities.NamespaceEntity;
import com.signatur3.data.jpa.entities.PromptSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptSectionRepository extends JpaRepository<PromptSectionEntity, Long> {
    PromptSectionEntity findByNamespaceAndName(NamespaceEntity namespace, String name);
}
