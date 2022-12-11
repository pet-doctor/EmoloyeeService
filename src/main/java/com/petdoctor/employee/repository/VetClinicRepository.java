package com.petdoctor.employee.repository;

import com.petdoctor.employee.model.entity.VetClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetClinicRepository extends JpaRepository<VetClinicEntity, Long> {

    Optional<VetClinicEntity> findVetClinicEntityById(Long id);
}
