package com.petdoctor.employee.tool.mapper;

import com.petdoctor.employee.model.dto.VetClinicDto;
import com.petdoctor.employee.model.entity.VetClinicEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-18T13:07:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class VetClinicMapperImpl implements VetClinicMapper {

    @Override
    public VetClinicDto vetClinicEntityToVetClinicDto(VetClinicEntity vetClinicEntity) {
        if ( vetClinicEntity == null ) {
            return null;
        }

        VetClinicDto vetClinicDto = new VetClinicDto();

        vetClinicDto.setId( vetClinicEntity.getId() );
        vetClinicDto.setAddress( vetClinicEntity.getAddress() );
        vetClinicDto.setEmail( vetClinicEntity.getEmail() );

        return vetClinicDto;
    }

    @Override
    public VetClinicEntity vetClinicDtoToVetClinicEntity(VetClinicDto vetClinicDto) {
        if ( vetClinicDto == null ) {
            return null;
        }

        VetClinicEntity vetClinicEntity = new VetClinicEntity();

        vetClinicEntity.setId( vetClinicDto.getId() );
        vetClinicEntity.setAddress( vetClinicDto.getAddress() );
        vetClinicEntity.setEmail( vetClinicDto.getEmail() );

        return vetClinicEntity;
    }
}
