package com.signatur3.data.jpa.repositories;

import com.signatur3.data.jpa.entities.NamespaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamespaceRepository extends JpaRepository<NamespaceEntity, Long> {
    NamespaceEntity findByName(String name);
}
