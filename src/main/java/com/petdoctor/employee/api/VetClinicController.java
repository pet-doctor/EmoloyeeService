package com.petdoctor.employee.api;

import com.petdoctor.employee.kafka.KafkaConstant;
import com.petdoctor.employee.kafka.KafkaService;
import com.petdoctor.employee.model.dto.VetClinicDto;
import com.petdoctor.employee.service.VetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/petdoctor/employee/vetclinic")
public class VetClinicController {

    private final VetClinicService vetClinicService;
    private final KafkaService kafkaService;

    @Autowired
    public VetClinicController(VetClinicService vetClinicService, KafkaService kafkaService) {
        this.vetClinicService = vetClinicService;
        this.kafkaService = kafkaService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerVetClinic(@RequestBody VetClinicDto vetClinicDto) {

        try {

            VetClinicDto registeredVetClinicDto = vetClinicService.registerVetClinic(vetClinicDto);
            kafkaService.sendMessage(vetClinicDto, KafkaConstant.KAFKA_EMPLOYEE_VETCLINIC_TOPIC);
            return ResponseEntity.ok(registeredVetClinicDto);
        } catch(RuntimeException e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateVetClinic(@RequestParam Long vetClinicId, @RequestBody VetClinicDto vetClinicDto) {

        try {

            VetClinicDto updatedVetClinicDto = vetClinicService.updateVetClinic(vetClinicId, vetClinicDto);
            kafkaService.sendMessage(vetClinicDto, KafkaConstant.KAFKA_EMPLOYEE_VETCLINIC_TOPIC);
            return ResponseEntity.ok(updatedVetClinicDto);
        } catch (RuntimeException e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/close")
    public ResponseEntity<?> closeVetClinic(@RequestParam Long vetClinicId) {

        try {

            vetClinicService.deleteVetClinic(vetClinicId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {

            return ResponseEntity.internalServerError().build();
        }
    }
}
