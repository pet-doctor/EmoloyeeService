package com.petdoctor.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAppointmentStateDto {

    @JsonProperty("appointment")
    private Long appointmentId;

    @JsonProperty("doctor")
    private Long doctorId;
}
