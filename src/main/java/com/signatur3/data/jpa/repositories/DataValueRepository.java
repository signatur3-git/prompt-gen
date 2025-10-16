package com.signatur3.data.jpa.repositories;

import com.signatur3.data.jpa.entities.DataTypeEntity;
import com.signatur3.data.jpa.entities.DataValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataValueRepository extends JpaRepository<DataValueEntity, Long> {
    List<DataValueEntity> findAllByDataType(DataTypeEntity dataType);
}
