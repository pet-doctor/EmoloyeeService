package com.petdoctor.employee.service.impl;

import com.petdoctor.employee.model.dto.DoctorDto;
import com.petdoctor.employee.model.entity.DoctorEntity;
import com.petdoctor.employee.repository.DoctorRepository;
import com.petdoctor.employee.service.DoctorService;
import com.petdoctor.employee.tool.exception.NotFoundEmployeeServiceException;
import com.petdoctor.employee.tool.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public DoctorDto registerDoctor(DoctorDto doctorDto) {

        DoctorEntity doctorEntity = doctorMapper.doctorDtoToDoctorEntity(doctorDto);

        return doctorMapper
                .doctorEntityToDoctorDto(doctorRepository.save(doctorEntity));
    }

    @Transactional
    public DoctorDto updateDoctor(Long doctorId, DoctorDto doctorDto) {

        DoctorEntity foundDoctorEntity = doctorRepository
                .findDoctorEntityById(doctorId)
                .orElse(null);

        if (foundDoctorEntity == null) {
            throw new NotFoundEmployeeServiceException(String.format("Doctor with the id: %d doesn't exist", doctorId));
        }

        deleteDoctor(doctorId);

        if (doctorDto.getId() != null)
            foundDoctorEntity.setId(doctorDto.getId());
        if (doctorDto.getName() != null && !Objects.equals(doctorDto.getName(), ""))
            foundDoctorEntity.setName(doctorDto.getName());
        if (doctorDto.getSurname() != null && !Objects.equals(doctorDto.getSurname(), ""))
            foundDoctorEntity.setSurname(doctorDto.getSurname());
        if (doctorDto.getDoctorOffice() != null)
            foundDoctorEntity.setDoctorOffice(doctorDto.getDoctorOffice());
        if (doctorDto.getEmail() != null && !Objects.equals(doctorDto.getEmail(), ""))
            foundDoctorEntity.setEmail(doctorDto.getEmail());
        if (doctorDto.getVetClinicId() != null)
            foundDoctorEntity.setVetClinicEntityId(doctorDto.getVetClinicId());

        return doctorMapper.doctorEntityToDoctorDto(doctorRepository.save(foundDoctorEntity));
    }

    @Transactional
    public void deleteDoctor(Long doctorId) {
        DoctorEntity foundDoctorEntity = doctorRepository.findById(doctorId).orElse(null);

        if (foundDoctorEntity == null) {
            throw new NotFoundEmployeeServiceException(String.format("Doctor with the id: %d doesn't exist", doctorId));
        }

        doctorRepository.delete(foundDoctorEntity);
    }
}
