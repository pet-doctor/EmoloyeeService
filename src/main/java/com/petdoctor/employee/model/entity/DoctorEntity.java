package com.petdoctor.employee.model.entity;

import com.petdoctor.employee.model.enums.DoctorCategory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "doctor_office", nullable = false, unique = true)
    private Integer doctorOffice;

    @Column(name = "category", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DoctorCategory doctorCategory;

    @Column(name = "vet_clinic_id", nullable = false)
    private Long vetClinicEntityId;

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof DoctorEntity))
            return false;

        DoctorEntity that = (DoctorEntity) o;

        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(surname, that.surname)
                && Objects.equals(email, that.email)
                && Objects.equals(doctorOffice, that.doctorOffice)
                && Objects.equals(vetClinicEntityId, that.vetClinicEntityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, doctorOffice, vetClinicEntityId);
    }
}
