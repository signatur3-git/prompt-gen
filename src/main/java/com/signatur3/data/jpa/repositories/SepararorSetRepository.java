package com.signatur3.data.jpa.repositories;

import com.signatur3.data.jpa.entities.SeparatorSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SepararorSetRepository extends JpaRepository<SeparatorSetEntity, Long> {
}
