package com.sesame.challange.web.controller;

import com.sesame.challange.dto.AppointmentDTO;
import com.sesame.challange.entity.AppointmentStatus;
import com.sesame.challange.mapper.AppointmentMapper;
import com.sesame.challange.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @PostMapping("/appointments")
    public AppointmentDTO createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentMapper.convertToDTO(appointmentService.save(appointmentMapper.convertToEntity(appointmentDTO)));
    }

    @PatchMapping("/appointments/{id}/{status}")
    public AppointmentDTO updateStatus(@PathVariable("id") String id, @PathVariable("status") AppointmentStatus status) {
        return appointmentService.updateAppointmentStatus(id, status).map(appointmentMapper::convertToDTO).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/appointments/{id}")
    public AppointmentDTO getAppointment(@PathVariable("id") String id) {
        return appointmentService.findById(id).map(appointmentMapper::convertToDTO).orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("/appointments/{id}")
    public void deleteAppointment(@PathVariable("id") String id) {
        appointmentService.deleteById(id);
    }

    @GetMapping("/appointments/{dateBegin}/{dateEnd}")
    public List<AppointmentDTO> getAppointmentsBetweenDates(@PathVariable("dateBegin") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateBegin, @PathVariable("dateEnd") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateEnd) {
        return appointmentService.findByAppointmentDateBetweenOrderByPrice(dateBegin, dateEnd).stream().map(appointmentMapper::convertToDTO).collect(Collectors.toList());
    }

}
