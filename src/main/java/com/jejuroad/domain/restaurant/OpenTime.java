package com.jejuroad.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;

import static lombok.AccessLevel.PACKAGE;

@Embeddable
@Getter(PACKAGE)
@NoArgsConstructor
@AllArgsConstructor(access = PACKAGE)
@EntityListeners(AuditingEntityListener.class)
public class OpenTime {

    public enum Day {
        MON, TUE, WED, THU, FRI, SAT, SUN;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
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
