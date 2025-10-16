package com.signatur3.data.jpa.repositories;

import com.signatur3.data.jpa.entities.DataTypeEntity;
import com.signatur3.data.jpa.entities.NamespaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTypeRepository extends JpaRepository<DataTypeEntity, Long> {
    DataTypeEntity findByNamespaceAndName(NamespaceEntity namespace, String name);
}
