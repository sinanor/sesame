package com.sesame.challange.repository;

import com.sesame.challange.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByAppointmentDateBetweenOrderByPrice(LocalDateTime dateBegin, LocalDateTime dateEnd);
}
