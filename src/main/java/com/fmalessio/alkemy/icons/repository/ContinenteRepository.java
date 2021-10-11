package com.fmalessio.alkemy.icons.repository;

import com.fmalessio.alkemy.icons.entity.ContinenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinenteRepository extends JpaRepository<ContinenteEntity, Long> {

}
