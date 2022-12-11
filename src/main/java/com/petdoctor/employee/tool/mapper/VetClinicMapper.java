package com.petdoctor.employee.tool.mapper;

import com.petdoctor.employee.model.dto.DoctorDto;
import com.petdoctor.employee.model.dto.VetClinicDto;
import com.petdoctor.employee.model.entity.DoctorEntity;
import com.petdoctor.employee.model.entity.VetClinicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VetClinicMapper {

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source="address", target = "address"),
            @Mapping(source = "email", target = "email"),
    })
    VetClinicDto vetClinicEntityToVetClinicDto(VetClinicEntity vetClinicEntity);

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source="address", target = "address"),
            @Mapping(source = "email", target = "email"),
    })
    VetClinicEntity vetClinicDtoToVetClinicEntity(VetClinicDto vetClinicDto);
}
