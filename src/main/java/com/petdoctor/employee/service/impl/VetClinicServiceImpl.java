package com.petdoctor.employee.service.impl;

import com.petdoctor.employee.model.dto.VetClinicDto;
import com.petdoctor.employee.model.entity.VetClinicEntity;
import com.petdoctor.employee.repository.VetClinicRepository;
import com.petdoctor.employee.service.VetClinicService;
import com.petdoctor.employee.tool.exception.NotFoundEmployeeServiceException;
import com.petdoctor.employee.tool.exception.ValidationEmployeeServiceException;
import com.petdoctor.employee.tool.mapper.VetClinicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VetClinicServiceImpl implements VetClinicService {

    private final VetClinicRepository vetClinicRepository;
    private final VetClinicMapper vetClinicMapper;

    @Autowired
    public VetClinicServiceImpl(VetClinicRepository vetClinicRepository, VetClinicMapper vetClinicMapper) {
        this.vetClinicRepository = vetClinicRepository;
        this.vetClinicMapper = vetClinicMapper;
    }

    public VetClinicDto registerVetClinic(VetClinicDto vetClinicDto) {

        if (vetClinicDto == null) {
            throw new ValidationEmployeeServiceException("VetClinic is null");
        }

        if (findVetClinicById(vetClinicDto.getId()) != null) {
            throw new ValidationEmployeeServiceException("Registered VetClinic is already exist!");
        }

        VetClinicEntity vetClinicEntity = mapDtoToEntity(vetClinicDto);

        return mapEntityToDto(
                vetClinicRepository.save(vetClinicEntity));
    }

    public VetClinicDto updateVetClinic(Long vetClinicId, VetClinicDto vetClinicDto) {

        VetClinicEntity vetClinicEntity = findVetClinicById(vetClinicId);

        if (vetClinicDto.getId() != null) vetClinicEntity.setId(vetClinicDto.getId());
        if (vetClinicDto.getAddress() != null) vetClinicEntity.setAddress(vetClinicDto.getAddress());
        if (vetClinicDto.getEmail() != null) vetClinicEntity.setEmail(vetClinicDto.getEmail());

        return mapEntityToDto(vetClinicEntity);
    }

    public void deleteVetClinic(Long vetClinicId) {

        VetClinicEntity foundVetClinicEntity = findVetClinicById(vetClinicId);

        vetClinicRepository.delete(foundVetClinicEntity);
    }

    private VetClinicEntity findVetClinicById(Long id) {

        return vetClinicRepository
                .findVetClinicEntityById(id)
                .orElseThrow(() ->
                        new NotFoundEmployeeServiceException("Vet clinic doesn't exist"));
    }

    private VetClinicDto mapEntityToDto(VetClinicEntity vetClinicEntity) {

        return vetClinicMapper.vetClinicEntityToVetClinicDto(vetClinicEntity);
    }

    private VetClinicEntity mapDtoToEntity(VetClinicDto vetClinicDto) {

        return vetClinicMapper.vetClinicDtoToVetClinicEntity(vetClinicDto);
    }
}
