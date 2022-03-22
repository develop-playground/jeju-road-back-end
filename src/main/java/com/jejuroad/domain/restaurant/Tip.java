package com.jejuroad.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static lombok.AccessLevel.PACKAGE;

@Entity
@Getter(PACKAGE)
@NoArgsConstructor
@AllArgsConstructor(access = PACKAGE)
public class Tip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String content;

}
