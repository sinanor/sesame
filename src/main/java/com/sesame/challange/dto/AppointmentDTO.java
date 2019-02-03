package com.sesame.challange.dto;

import com.sesame.challange.entity.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime appointmentDate;
    private Integer appointmentDuration;
    private String nameOfDoctor;
    private AppointmentStatus status;
    private Long price;
}
