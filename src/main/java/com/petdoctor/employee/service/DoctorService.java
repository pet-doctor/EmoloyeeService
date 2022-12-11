package com.petdoctor.employee.service;

import com.petdoctor.employee.model.dto.DoctorDto;

public interface DoctorService {

    DoctorDto registerDoctor(DoctorDto doctorDto);
    DoctorDto updateDoctor(Long doctorId, DoctorDto doctorDto);
    void deleteDoctor(Long doctorId);
}
