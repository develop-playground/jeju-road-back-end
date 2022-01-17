package com.jejuroad.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Accessors
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OpenTime {

    enum Day {
        MON, TUE, WED, THU, FRI, SAT, SUN;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private Day day;

    @Column(nullable = false)
    private LocalTime operationStart;

    @Column(nullable = false)
    private LocalTime operationEnd;

    @Column(nullable = false)
    private LocalTime breakStart;

    @Column(nullable = false)
    private LocalTime breakEnd;

}
