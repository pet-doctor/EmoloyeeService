package com.petdoctor.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollClientDto implements Serializable {

    @JsonProperty("doctor")
    private Long doctorId;

    @JsonProperty("appointment")
    private Long appointmentId;

    @JsonProperty("client")
    private Long clientId;

    @Override
    public String toString() {
        return "EnrollClientDto{" +
                "doctorId=" + doctorId +
                ", appointmentId=" + appointmentId +
                ", clientId=" + clientId +
                '}';
    }
}
