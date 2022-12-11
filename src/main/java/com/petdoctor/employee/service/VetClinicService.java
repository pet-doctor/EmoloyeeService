package com.petdoctor.employee.service;


import com.petdoctor.employee.model.dto.VetClinicDto;

public interface VetClinicService {

    VetClinicDto registerVetClinic(VetClinicDto vetClinicDto);
    VetClinicDto updateVetClinic(Long vetClinicId, VetClinicDto vetClinicDto);
    void deleteVetClinic(Long vetClinicId);
}
