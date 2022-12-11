package com.petdoctor.employee.service.impl;

import com.petdoctor.employee.model.dto.VetClinicDto;
import com.petdoctor.employee.model.entity.VetClinicEntity;
import com.petdoctor.employee.repository.VetClinicRepository;
import com.petdoctor.employee.service.VetClinicService;
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

        VetClinicEntity vetClinicEntity = vetClinicMapper.vetClinicDtoToVetClinicEntity(vetClinicDto);

        return vetClinicMapper
                .vetClinicEntityToVetClinicDto(vetClinicRepository.save(vetClinicEntity));
    }

    @Transactional
    public VetClinicDto updateVetClinic(Long vetClinicId, VetClinicDto vetClinicDto) {

        VetClinicEntity foundVetClinicEntity = vetClinicRepository
                .findVetClinicEntityById(vetClinicId).orElse(null);

        if (foundVetClinicEntity == null) {
            throw new RuntimeException();
        }

        deleteVetClinic(vetClinicId);

        foundVetClinicEntity.setId(vetClinicDto.getId());
        foundVetClinicEntity.setAddress(vetClinicDto.getAddress());
        foundVetClinicEntity.setEmail(vetClinicDto.getEmail());

        return vetClinicMapper
                .vetClinicEntityToVetClinicDto(vetClinicRepository.save(foundVetClinicEntity));
    }

    @Transactional
    public void deleteVetClinic(Long vetClinicId) {

        VetClinicEntity vetClinicEntity = vetClinicRepository
                .findVetClinicEntityById(vetClinicId).orElse(null);

        if (vetClinicEntity == null) {
            throw new RuntimeException();
        }

        vetClinicRepository.delete(vetClinicEntity);
    }
}
