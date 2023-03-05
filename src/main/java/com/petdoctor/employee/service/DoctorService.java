package com.petdoctor.employee.service;

import com.petdoctor.employee.model.dto.DoctorDto;
import com.petdoctor.employee.model.enums.DoctorCategory;
import com.petdoctor.employee.model.enums.VetClinicAddress;

import java.util.List;

public interface DoctorService {

    DoctorDto registerDoctor(DoctorDto doctorDto);
    DoctorDto updateDoctor(Long doctorId, DoctorDto doctorDto);
    List<DoctorDto> getDoctorsByCategory(DoctorCategory doctorCategory, VetClinicAddress vetClinicAddress);
    void deleteDoctor(Long doctorId);
}
