package com.sesame.challange.dto;

import com.sesame.challange.entity.Appointment;
import com.sesame.challange.entity.AppointmentStatus;
import com.sesame.challange.mapper.AppointmentMapper;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class AppointmentMapperTest {

    @Test
    public void shouldMapAppointmentDTOtoAppointmentCorrectly() {
        //given
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .id("97f40e95-880e-4cf8-ab79-cf3bb1dfe168")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now())
                .appointmentDate(LocalDateTime.now().plusDays(2))
                .appointmentDuration(120)
                .nameOfDoctor("Micheal Wright")
                .status(AppointmentStatus.BOOKED)
                .price(4999L)
                .build();
        //when
        Appointment appointment = new AppointmentMapper().convertToEntity(appointmentDTO);
        //then
        assertEquals(appointmentDTO.getId(), appointment.getId());
        assertEquals(appointmentDTO.getCreatedAt(), appointment.getCreatedAt());
        assertEquals(appointmentDTO.getUpdatedAt(), appointment.getUpdatedAt());
        assertEquals(appointmentDTO.getAppointmentDate(), appointment.getAppointmentDate());
        assertEquals(appointmentDTO.getAppointmentDuration(), appointment.getAppointmentDuration());
        assertEquals(appointmentDTO.getNameOfDoctor(), appointment.getNameOfDoctor());
        assertEquals(appointmentDTO.getStatus(), appointment.getStatus());
        assertEquals(appointmentDTO.getPrice(), appointment.getPrice());
    }

    @Test
    public void shouldMapAppointmenttoAppointmentDTOCorrectly() {
        //given
        Appointment appointment = Appointment.builder()
                .id("97f40e95-880e-4cf8-ab79-cf3bb1dfe168")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now())
                .appointmentDate(LocalDateTime.now().plusDays(2))
                .appointmentDuration(120)
                .nameOfDoctor("Micheal Wright")
                .status(AppointmentStatus.BOOKED)
                .price(4999L)
                .build();
        //when
        AppointmentDTO appointmentDTO = new AppointmentMapper().convertToDTO(appointment);
        //then
        assertEquals(appointment.getId(), appointmentDTO.getId());
        assertEquals(appointment.getCreatedAt(), appointmentDTO.getCreatedAt());
        assertEquals(appointment.getUpdatedAt(), appointmentDTO.getUpdatedAt());
        assertEquals(appointment.getAppointmentDate(), appointmentDTO.getAppointmentDate());
        assertEquals(appointment.getAppointmentDuration(), appointmentDTO.getAppointmentDuration());
        assertEquals(appointment.getNameOfDoctor(), appointmentDTO.getNameOfDoctor());
        assertEquals(appointment.getStatus(), appointmentDTO.getStatus());
        assertEquals(appointment.getPrice(), appointmentDTO.getPrice());
    }
}
