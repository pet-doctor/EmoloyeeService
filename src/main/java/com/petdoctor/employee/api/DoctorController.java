package com.petdoctor.employee.api;

import com.petdoctor.employee.kafka.KafkaConstant;
import com.petdoctor.employee.kafka.KafkaService;
import com.petdoctor.employee.model.dto.DoctorDto;
import com.petdoctor.employee.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/petdoctor/employee/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final KafkaService kafkaService;

    @Autowired
    public DoctorController(DoctorService doctorService, KafkaService kafkaService) {
        this.doctorService = doctorService;
        this.kafkaService = kafkaService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorDto doctorDto) {

        try {

            DoctorDto registeredDoctorDto = doctorService.registerDoctor(doctorDto);
            kafkaService.sendMessage(doctorDto, KafkaConstant.KAFKA_EMPLOYEE_DOCTOR_TOPIC);
            return ResponseEntity.ok(registeredDoctorDto);
        } catch(RuntimeException e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDoctor(@RequestParam Long doctorId, @RequestBody DoctorDto doctorDto) {

        try {

            DoctorDto updatedDoctorDto = doctorService.updateDoctor(doctorId, doctorDto);
            kafkaService.sendMessage(doctorDto, KafkaConstant.KAFKA_EMPLOYEE_DOCTOR_TOPIC);
            return ResponseEntity.ok(updatedDoctorDto);
        } catch(Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/sack")
    public ResponseEntity<?> sackDoctor(@RequestParam Long doctorId) {

        try {

            doctorService.deleteDoctor(doctorId);
            return ResponseEntity.ok("Doctor with id: " + doctorId + " has been sacked");
        } catch (RuntimeException e) {

            return ResponseEntity.internalServerError().build();
        }
    }
}
