package com.petdoctor.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAppointmentStateDto implements Serializable {

    @JsonProperty("appointment")
    private Long appointmentId;

    @JsonProperty("doctor")
    private Long doctorId;

    @Override
    public String toString() {
        return "ChangeAppointmentStateDto{" +
                "appointmentId=" + appointmentId +
                ", doctorId=" + doctorId +
                '}';
    }
}
