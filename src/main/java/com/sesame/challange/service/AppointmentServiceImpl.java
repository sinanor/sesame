package com.sesame.challange.service;

import com.sesame.challange.entity.Appointment;
import com.sesame.challange.entity.AppointmentStatus;
import com.sesame.challange.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Optional<Appointment> findById(String id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findByAppointmentDateBetweenOrderByPrice(LocalDateTime dateBegin, LocalDateTime dateEnd) {
        return appointmentRepository.findByAppointmentDateBetweenOrderByPrice(dateBegin, dateEnd);
    }

    @Override
    public Optional<Appointment> updateAppointmentStatus(String id, AppointmentStatus status) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            appointment.setStatus(status);
            Appointment appointmentUpdated = appointmentRepository.save(appointment);
            return Optional.of(appointmentUpdated);
        }
        return appointmentOptional;
    }

}
