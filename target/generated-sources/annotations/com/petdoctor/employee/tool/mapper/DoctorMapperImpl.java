package com.petdoctor.employee.tool.mapper;

import com.petdoctor.employee.model.dto.DoctorDto;
import com.petdoctor.employee.model.entity.DoctorEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-11T00:40:15+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDto doctorEntityToDoctorDto(DoctorEntity doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setId( doctor.getId() );
        doctorDto.setName( doctor.getName() );
        doctorDto.setSurname( doctor.getSurname() );
        doctorDto.setEmail( doctor.getEmail() );
        doctorDto.setDoctorOffice( doctor.getDoctorOffice() );
        doctorDto.setVetClinicId( doctor.getVetClinicEntityId() );

        return doctorDto;
    }

    @Override
    public DoctorEntity doctorDtoToDoctorEntity(DoctorDto doctorDto) {
        if ( doctorDto == null ) {
            return null;
        }

        DoctorEntity doctorEntity = new DoctorEntity();

        doctorEntity.setId( doctorDto.getId() );
        doctorEntity.setName( doctorDto.getName() );
        doctorEntity.setSurname( doctorDto.getSurname() );
        doctorEntity.setEmail( doctorDto.getEmail() );
        doctorEntity.setDoctorOffice( doctorDto.getDoctorOffice() );
        doctorEntity.setVetClinicEntityId( doctorDto.getVetClinicId() );

        return doctorEntity;
    }
}
