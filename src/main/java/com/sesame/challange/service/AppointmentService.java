package com.sesame.challange.service;

import com.sesame.challange.entity.Appointment;
import com.sesame.challange.entity.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Optional<Appointment> findById(String id);

    void deleteById(String id);

    Appointment save(Appointment appointment);

    Optional<Appointment> updateAppointmentStatus(String id, AppointmentStatus status);

    List<Appointment> findByAppointmentDateBetweenOrderByPrice(LocalDateTime dateBegin, LocalDateTime dateEnd);
}
