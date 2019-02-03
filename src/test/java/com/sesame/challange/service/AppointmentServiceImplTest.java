package com.sesame.challange.service;

import com.sesame.challange.entity.Appointment;
import com.sesame.challange.entity.AppointmentStatus;
import com.sesame.challange.repository.AppointmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class AppointmentServiceImplTest {
    @Autowired
    private AppointmentService appointmentService;
    @MockBean
    private AppointmentRepository appointmentRepository;

    @Test
    public void deleteByIdTest() {
        appointmentService.deleteById("41828a52-44b9-44f5-8bbd-7665c2d7fe52");
        Mockito.verify(appointmentRepository).deleteById("41828a52-44b9-44f5-8bbd-7665c2d7fe52");
    }

    @Test
    public void saveTest() {
        Appointment appointment = Appointment.builder().id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").build();
        appointmentService.save(appointment);
        Mockito.verify(appointmentRepository).save(appointment);
    }

    @Test
    public void findByIdTest() {
        appointmentService.findById("41828a52-44b9-44f5-8bbd-7665c2d7fe52");
        Mockito.verify(appointmentRepository).findById("41828a52-44b9-44f5-8bbd-7665c2d7fe52");
    }

    @Test
    public void findByAppointmentDateBetweenOrderByPriceTest() {
        LocalDateTime dateBegin = LocalDateTime.now();
        LocalDateTime dateEnd = LocalDateTime.now().plusDays(1);
        appointmentService.findByAppointmentDateBetweenOrderByPrice(dateBegin, dateEnd);
        Mockito.verify(appointmentRepository).findByAppointmentDateBetweenOrderByPrice(dateBegin, dateEnd);
    }

    @Test
    public void updateAppointmentStatus() {
        Appointment appointment = Appointment.builder().id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").status(AppointmentStatus.AVAILABLE).build();
        given(appointmentRepository.findById(appointment.getId())).willReturn(Optional.of(appointment));
        Appointment appointmentUpdate = Appointment.builder().id("41828a52-44b9-44f5-8bbd-7665c2d7fe52").status(AppointmentStatus.BOOKED).build();
        given(appointmentRepository.save(appointmentUpdate)).willReturn(appointmentUpdate);
        appointmentService.updateAppointmentStatus("41828a52-44b9-44f5-8bbd-7665c2d7fe52", AppointmentStatus.BOOKED);
        Mockito.verify(appointmentRepository).findById("41828a52-44b9-44f5-8bbd-7665c2d7fe52");
        Mockito.verify(appointmentRepository).save(appointmentUpdate);
    }

    @TestConfiguration
    static class DeveloperServiceImplTestContextConfiguration {
        @Bean
        public AppointmentService developerService() {
            return new AppointmentServiceImpl();
        }
    }
}
