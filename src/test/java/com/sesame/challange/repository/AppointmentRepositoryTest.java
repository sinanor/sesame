package com.sesame.challange.repository;

import com.sesame.challange.entity.Appointment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppointmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    public void shouldReturnScheduledAppointment() {
        // given
        Appointment appointment1 = entityManager.persist(Appointment.builder().price(4999L).appointmentDate(LocalDateTime.parse("2019-01-01T10:11:30")).build());
        Appointment appointment2 = entityManager.persist(Appointment.builder().price(6999L).appointmentDate(LocalDateTime.parse("2019-02-01T10:11:30")).build());
        Appointment appointment3 = entityManager.persist(Appointment.builder().price(3999L).appointmentDate(LocalDateTime.parse("2019-02-01T10:13:30")).build());
        Appointment appointment4 = entityManager.persist(Appointment.builder().price(3999L).appointmentDate(LocalDateTime.parse("2019-02-01T10:15:30")).build());
        Appointment appointment5 = entityManager.persist(Appointment.builder().price(5999L).appointmentDate(LocalDateTime.parse("2019-03-01T10:11:30")).build());
        entityManager.flush();

        //when
        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateBetweenOrderByPrice(LocalDateTime.parse("2019-02-01T10:11:30"), LocalDateTime.parse("2019-02-01T10:13:30"));
        assertEquals(2, appointmentList.size());
        assertEquals(appointment3.getId(), appointmentList.get(0).getId());
        assertEquals(appointment2.getId(), appointmentList.get(1).getId());
    }
}
