package com.petdoctor.employee.api;

import com.petdoctor.employee.kafka.KafkaConstant;
import com.petdoctor.employee.kafka.KafkaService;
import com.petdoctor.employee.model.dto.DoctorDto;
import com.petdoctor.employee.model.dto.ChangeAppointmentStateDto;
import com.petdoctor.employee.model.dto.EnrollClientDto;
import com.petdoctor.employee.service.DoctorService;
import org.apache.tomcat.util.http.parser.MediaType;
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

    @PostMapping("/openAppointment")
    public ResponseEntity<?> openAppointment(@RequestParam Long doctorId, @RequestParam Long appointmentId) {

        try {

            ChangeAppointmentStateDto changeAppointmentStateDto = new ChangeAppointmentStateDto(appointmentId, doctorId);

            kafkaService.sendMessage(changeAppointmentStateDto, KafkaConstant.KAFKA_ENROLLMENT_OPEN_APPOINTMENT_TOPIC);
            return ResponseEntity.ok().build();
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/enrollClient")
    public ResponseEntity<?> enrollClient(@RequestParam Long doctorId, @RequestParam Long appointmentId, @RequestParam Long clientId) {

        try {

            EnrollClientDto enrollClientDto = new EnrollClientDto(doctorId, appointmentId, clientId);

            kafkaService.sendMessage(enrollClientDto, KafkaConstant.KAFKA_ENROLLMENT_CLIENT_GROUP);
            return ResponseEntity.ok().build();
        } catch(Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/closeAppointment")
    public ResponseEntity<?> closeAppointment(@RequestParam Long doctorId, @RequestBody Long appointmentId) {

        try {

            ChangeAppointmentStateDto changeAppointmentStateDto = new ChangeAppointmentStateDto(appointmentId, doctorId);

            kafkaService.sendMessage(changeAppointmentStateDto, KafkaConstant.KAFKA_ENROLLMENT_CLOSE_APPOINTMENT_TOPIC);
            return ResponseEntity.ok().build();
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }
}
