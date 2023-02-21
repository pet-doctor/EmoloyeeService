package com.petdoctor.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.petdoctor.employee.model.enums.VetClinicAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VetClinicDto implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("address")
    private VetClinicAddress address;

    @JsonProperty("email")
    private String email;

    @Override
    public String toString() {
        return "VetClinicDto{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
