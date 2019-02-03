package com.sesame.challange.mapper;

import com.sesame.challange.dto.AppointmentDTO;
import com.sesame.challange.entity.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper implements Mapper<AppointmentDTO, Appointment> {
    @Override
    public Appointment convertToEntity(AppointmentDTO appointmentDTO) {
        return Appointment.builder()
                .id(appointmentDTO.getId())
                .createdAt(appointmentDTO.getCreatedAt())
                .updatedAt(appointmentDTO.getUpdatedAt())
                .appointmentDate(appointmentDTO.getAppointmentDate())
                .appointmentDuration(appointmentDTO.getAppointmentDuration())
                .nameOfDoctor(appointmentDTO.getNameOfDoctor())
                .status(appointmentDTO.getStatus())
                .price(appointmentDTO.getPrice())
                .build();
    }

    @Override
    public AppointmentDTO convertToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .createdAt(appointment.getCreatedAt())
                .updatedAt(appointment.getUpdatedAt())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentDuration(appointment.getAppointmentDuration())
                .nameOfDoctor(appointment.getNameOfDoctor())
                .status(appointment.getStatus())
                .price(appointment.getPrice())
                .build();
    }
}
