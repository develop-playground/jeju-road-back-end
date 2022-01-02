package com.jejuroad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(nullable = false, length = 7)
    private String zipcode;

    @Column(nullable = false, length = 15)
    private String state;

    @Column(nullable = false, length = 15)
    private String city;

    @Column(nullable = false, length = 20)
    private String simple;

    @Column(nullable = false, length = 50)
    private String detail;

    @Column(nullable = false, columnDefinition = "REAL")
    private double latitude;

    @Column(nullable = false, columnDefinition = "REAL")
    private double longitude;

}
