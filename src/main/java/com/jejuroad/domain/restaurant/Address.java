package com.jejuroad.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static lombok.AccessLevel.PACKAGE;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(nullable = false, length = 7)
    private String zipcode;

    @Column(nullable = false, length = 15)
    private String state;

    @Column(nullable = false, length = 15)
    private String city;

    @Column(name = "simple_address", nullable = false, length = 20)
    private String simple;

    @Column(name = "detail_address", nullable = false, length = 50)
    private String detail;

    @Column(nullable = false, columnDefinition = "REAL")
    private double latitude;

    @Column(nullable = false, columnDefinition = "REAL")
    private double longitude;

}
