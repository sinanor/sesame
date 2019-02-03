package com.sesame.challange.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sesame.challange.dto.AppointmentDTO;
import com.sesame.challange.entity.Appointment;
import com.sesame.challange.entity.AppointmentStatus;
import com.sesame.challange.mapper.AppointmentMapper;
import com.sesame.challange.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AppointmentService service;
    @MockBean
    private AppointmentMapper mapper;

    @Test
    public void createAppointmentTest() throws Exception {
        AppointmentDTO appointmentDTO = AppointmentDTO.builder().
                status(AppointmentStatus.AVAILABLE).appointmentDuration(60).nameOfDoctor("John Wright")
                .appointmentDate(LocalDateTime.parse("2019-01-01T10:11:30")).build();
        Appointment appointment = Appointment.builder().status(AppointmentStatus.AVAILABLE).appointmentDuration(60).nameOfDoctor("John Wright")
                .appointmentDate(LocalDateTime.parse("2019-01-01T10:11:30")).build();
        given(service.save(appointment)).willReturn(appointment);
        given(mapper.convertToDTO(appointment)).willReturn(appointmentDTO);
        given(mapper.convertToEntity(appointmentDTO)).willReturn(appointment);
        mvc.perform(post("/api/appointments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(appointment.getStatus().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.appointmentDuration", is(appointment.getAppointmentDuration())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameOfDoctor", is(appointment.getNameOfDoctor())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.appointmentDate", is(LocalDateTime.parse("2019-01-01T10:11:30").toString())))
        ;
        Mockito.verify(service).save(appointment);
    }

    @Test
    public void deleteAppointmentTest() throws Exception {
        mvc.perform(delete("/api/appointments/97f40e95-880e-4cf8-ab79-cf3bb1dfe168")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(service).deleteById("97f40e95-880e-4cf8-ab79-cf3bb1dfe168");
    }

    @Test
    public void getAppointmentByIdTest() throws Exception {
        Appointment appointment = Appointment.builder().id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").build();
        AppointmentDTO appointmentDTO = AppointmentDTO.builder().id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").build();
        given(service.findById(appointment.getId())).willReturn(Optional.of(appointment));
        given(mapper.convertToDTO(appointment)).willReturn(appointmentDTO);
        mvc.perform(get("/api/appointments/" + appointment.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(appointment.getId())));
        Mockito.verify(service).findById("41828a52-44b9-44f5-8bbd-7665c2d7fe52");
    }

    @Test
    public void getAppointmentsBetweenDates() throws Exception {
        LocalDateTime dateBegin = LocalDateTime.parse("2019-01-01T10:11:30");
        LocalDateTime dateEnd = LocalDateTime.parse("2019-01-03T10:11:30");
        Appointment appointment = Appointment.builder().id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").build();
        AppointmentDTO appointmentDTO = AppointmentDTO.builder().id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").build();
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        given(mapper.convertToDTO(appointment)).willReturn(appointmentDTO);
        given(service.findByAppointmentDateBetweenOrderByPrice(dateBegin, dateEnd)).willReturn(appointmentList);
        mvc.perform(get("/api/appointments/{dateBegin}/{dateEnd}", dateBegin, dateEnd)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(appointment.getId())));
        Mockito.verify(service).findByAppointmentDateBetweenOrderByPrice(dateBegin, dateEnd);
    }

    @Test
    public void updateStatusTest() throws Exception {
        Appointment appointment = Appointment.builder().status(AppointmentStatus.AVAILABLE).id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").build();
        Appointment appointmentUpdated = Appointment.builder().status(AppointmentStatus.BOOKED).id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").build();
        AppointmentDTO appointmentDTO = AppointmentDTO.builder().status(AppointmentStatus.BOOKED).id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").build();
        given(service.updateAppointmentStatus(appointment.getId(), AppointmentStatus.BOOKED)).willReturn(Optional.of(appointmentUpdated));
        given(mapper.convertToDTO(appointmentUpdated)).willReturn(appointmentDTO);
        mvc.perform(patch("/api/appointments/{id}/{status}", appointment.getId(), AppointmentStatus.BOOKED)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(appointment.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(AppointmentStatus.BOOKED.toString())));
        Mockito.verify(service).updateAppointmentStatus("41828a52-44b9-44f5-8bbd-7665c2d7fe52", AppointmentStatus.BOOKED);
    }

}
