package com.petdoctor.employee.tool.mapper;

import com.petdoctor.employee.model.dto.DoctorDto;
import com.petdoctor.employee.model.entity.DoctorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source="name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "doctorOffice", target = "doctorOffice"),
            @Mapping(source = "vetClinicEntityId", target = "vetClinicId")
    })
    DoctorDto doctorEntityToDoctorDto(DoctorEntity doctor);

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source="name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "doctorOffice", target = "doctorOffice"),
            @Mapping(source = "vetClinicId", target = "vetClinicEntityId")
    })
    DoctorEntity doctorDtoToDoctorEntity(DoctorDto doctorDto);
}
