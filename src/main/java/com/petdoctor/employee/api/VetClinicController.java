package com.petdoctor.employee.api;

import com.petdoctor.employee.kafka.KafkaConstant;
import com.petdoctor.employee.kafka.KafkaService;
import com.petdoctor.employee.model.dto.VetClinicDto;
import com.petdoctor.employee.service.VetClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/petdoctor/employee/vetclinic")
public class VetClinicController {

    private final VetClinicService vetClinicService;
    private final KafkaService kafkaService;

    @PostMapping("/register")
    public ResponseEntity<?> registerVetClinic(@RequestBody VetClinicDto vetClinicDto) {
        VetClinicDto registeredVetClinicDto = vetClinicService.registerVetClinic(vetClinicDto);
        kafkaService.sendMessage(vetClinicDto, KafkaConstant.KAFKA_EMPLOYEE_VETCLINIC_TOPIC);

        return ResponseEntity.ok(registeredVetClinicDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateVetClinic(@RequestParam Long vetClinicId, @RequestBody VetClinicDto vetClinicDto) {
        VetClinicDto updatedVetClinicDto = vetClinicService.updateVetClinic(vetClinicId, vetClinicDto);
        kafkaService.sendMessage(vetClinicDto, KafkaConstant.KAFKA_EMPLOYEE_VETCLINIC_TOPIC);

        return ResponseEntity.ok(updatedVetClinicDto);
    }

    @DeleteMapping("/close")
    public ResponseEntity<?> closeVetClinic(@RequestParam Long vetClinicId) {
        vetClinicService.deleteVetClinic(vetClinicId);

        return ResponseEntity.ok().build();
    }
}
