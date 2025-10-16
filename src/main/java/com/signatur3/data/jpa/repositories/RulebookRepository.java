package com.signatur3.data.jpa.repositories;

import com.signatur3.data.jpa.entities.NamespaceEntity;
import com.signatur3.data.jpa.entities.RulebookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulebookRepository extends JpaRepository<RulebookEntity, Long> {
    RulebookEntity findByNamespaceAndName(NamespaceEntity namespace, String name);
}
