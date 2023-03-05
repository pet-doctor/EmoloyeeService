package com.petdoctor.employee.repository;

import com.petdoctor.employee.model.entity.DoctorEntity;
import com.petdoctor.employee.model.enums.DoctorCategory;
import com.petdoctor.employee.model.enums.VetClinicAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Optional<DoctorEntity> findDoctorEntityById(Long doctorEntityId);

    @Query(value =
            "select d from DoctorEntity as d \n" +
                    "where d.doctorCategory = (:doctorCategory)")
    Optional<List<DoctorEntity>> findDoctorEntityByDoctorCategory(@Param("doctorCategory") DoctorCategory doctorCategory);

    @Query(value =
            "select d from DoctorEntity as d \n" +
                    "inner join VetClinicEntity as v \n" +
                    "on d.vetClinicEntityId = v.id \n" +
                    "where d.doctorCategory = (:doctorCategory) and v.address = (:vetClinicAddress)")
    Optional<List<DoctorEntity>> findDoctorEntityByDoctorCategory(@Param("doctorCategory") DoctorCategory doctorCategory,
                                                                  @Param("vetClinicAddress") VetClinicAddress vetClinicAddress);
}
