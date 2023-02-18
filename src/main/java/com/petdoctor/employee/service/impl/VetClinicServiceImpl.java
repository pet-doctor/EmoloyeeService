package com.petdoctor.employee.service.impl;

import com.petdoctor.employee.model.dto.VetClinicDto;
import com.petdoctor.employee.model.entity.VetClinicEntity;
import com.petdoctor.employee.repository.VetClinicRepository;
import com.petdoctor.employee.service.VetClinicService;
import com.petdoctor.employee.tool.exception.NotFoundEmployeeServiceException;
import com.petdoctor.employee.tool.exception.ValidationEmployeeServiceException;
import com.petdoctor.employee.tool.mapper.VetClinicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VetClinicServiceImpl implements VetClinicService {

    private final VetClinicRepository vetClinicRepository;
    private final VetClinicMapper vetClinicMapper;

    public VetClinicDto registerVetClinic(VetClinicDto vetClinicDto) {

        if (vetClinicDto == null) {
            throw new ValidationEmployeeServiceException("Vet Clinic is empty");
        }

        if (findVetClinicById(vetClinicDto.getId()).isPresent()) {
            throw new ValidationEmployeeServiceException(
                    String.format("Vet Clinic with the id: %d is already exist!", vetClinicDto.getId()));
        }

        VetClinicEntity vetClinicEntity = mapDtoToEntity(vetClinicDto);

        return mapEntityToDto(
                vetClinicRepository.save(vetClinicEntity));
    }

    public VetClinicDto updateVetClinic(VetClinicDto vetClinicDto) {

        if (vetClinicDto == null) {
            throw new ValidationEmployeeServiceException("Vet Clinic is empty");
        }

        VetClinicEntity vetClinicEntity = findVetClinicById(vetClinicDto.getId())
                .orElseThrow(() ->
                        new NotFoundEmployeeServiceException(
                                String.format("Vet Clinic with the id: %d doesn't exist!", vetClinicDto.getId())));

        if (vetClinicDto.getAddress() != null) vetClinicEntity.setAddress(vetClinicDto.getAddress());
        if (vetClinicDto.getEmail() != null) vetClinicEntity.setEmail(vetClinicDto.getEmail());

        return mapEntityToDto(vetClinicEntity);
    }

    public void deleteVetClinic(Long vetClinicId) {

        VetClinicEntity foundVetClinicEntity = findVetClinicById(vetClinicId)
                .orElseThrow(() ->
                        new NotFoundEmployeeServiceException(
                                String.format("Vet Clinic with the id: %d doesn't exist!", vetClinicId)));

        vetClinicRepository.delete(foundVetClinicEntity);
    }

    private Optional<VetClinicEntity> findVetClinicById(Long id) {

        return vetClinicRepository.findVetClinicEntityById(id);
    }

    private VetClinicDto mapEntityToDto(VetClinicEntity vetClinicEntity) {

        return vetClinicMapper.vetClinicEntityToVetClinicDto(vetClinicEntity);
    }

    private VetClinicEntity mapDtoToEntity(VetClinicDto vetClinicDto) {

        return vetClinicMapper.vetClinicDtoToVetClinicEntity(vetClinicDto);
    }
}
