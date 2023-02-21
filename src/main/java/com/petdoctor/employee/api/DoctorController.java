package com.petdoctor.employee.api;

import com.petdoctor.employee.kafka.KafkaConstant;
import com.petdoctor.employee.kafka.KafkaService;
import com.petdoctor.employee.model.dto.ChangeAppointmentStateDto;
import com.petdoctor.employee.model.dto.DoctorDto;
import com.petdoctor.employee.model.dto.EnrollClientDto;
import com.petdoctor.employee.model.enums.DoctorCategory;
import com.petdoctor.employee.model.enums.VetClinicAddress;
import com.petdoctor.employee.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/petdoctor/employee/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final KafkaService kafkaService;

    @GetMapping
    public ResponseEntity<?> getDoctorsByCategory(@RequestParam DoctorCategory doctorCategory,
                                                  @RequestParam VetClinicAddress vetClinicAddress) {

        List<DoctorDto> foundDoctors = doctorService.getDoctorsByCategory(doctorCategory, vetClinicAddress);

        return ResponseEntity.ok(foundDoctors);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorDto doctorDto) {

        DoctorDto registeredDoctorDto = doctorService.registerDoctor(doctorDto);

        kafkaService.sendMessage(doctorDto, KafkaConstant.KAFKA_EMPLOYEE_DOCTOR_TOPIC);
        return ResponseEntity.ok(registeredDoctorDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDoctor(@RequestParam Long doctorId, @RequestBody DoctorDto doctorDto) {

        DoctorDto updatedDoctorDto = doctorService.updateDoctor(doctorId, doctorDto);

        kafkaService.sendMessage(doctorDto, KafkaConstant.KAFKA_EMPLOYEE_DOCTOR_TOPIC);
        return ResponseEntity.ok(updatedDoctorDto);
    }

    @DeleteMapping("/sack")
    public ResponseEntity<?> sackDoctor(@RequestParam Long doctorId) {

        doctorService.deleteDoctor(doctorId);

        return ResponseEntity.ok("Doctor with id: " + doctorId + " has been sacked");
    }

    @PostMapping("/openAppointment")
    public ResponseEntity<?> openAppointment(@RequestParam Long doctorId, @RequestParam Long appointmentId) {

        ChangeAppointmentStateDto changeAppointmentStateDto = new ChangeAppointmentStateDto(appointmentId, doctorId);

        kafkaService.sendMessage(changeAppointmentStateDto, KafkaConstant.KAFKA_ENROLLMENT_OPEN_APPOINTMENT_TOPIC);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/enrollClient")
    public ResponseEntity<?> enrollClient(@RequestParam Long doctorId, @RequestParam Long appointmentId, @RequestParam Long clientId) {

        EnrollClientDto enrollClientDto = new EnrollClientDto(doctorId, appointmentId, clientId);

        kafkaService.sendMessage(enrollClientDto, KafkaConstant.KAFKA_ENROLLMENT_CLIENT_GROUP);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/closeAppointment")
    public ResponseEntity<?> closeAppointment(@RequestParam Long doctorId, @RequestBody Long appointmentId) {

        ChangeAppointmentStateDto changeAppointmentStateDto = new ChangeAppointmentStateDto(appointmentId, doctorId);

        kafkaService.sendMessage(changeAppointmentStateDto, KafkaConstant.KAFKA_ENROLLMENT_CLOSE_APPOINTMENT_TOPIC);
        return ResponseEntity.ok().build();
    }
}
