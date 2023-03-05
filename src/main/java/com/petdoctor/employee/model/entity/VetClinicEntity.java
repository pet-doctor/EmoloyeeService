package com.petdoctor.employee.model.entity;

import com.petdoctor.employee.model.enums.VetClinicAddress;
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
@Builder
@Entity
@Table(name = "vet_clinic")
public class VetClinicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private VetClinicAddress address;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof VetClinicEntity))
            return false;

        VetClinicEntity that = (VetClinicEntity) o;

        return Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, email);
    }
}
