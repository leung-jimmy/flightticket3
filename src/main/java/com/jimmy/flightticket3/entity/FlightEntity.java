package com.jimmy.flightticket3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "flights")
public class FlightEntity implements Serializable {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略，例如自增
    @Column(name = "flight_id")
    private int flightId;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_date_time")
    private OffsetDateTime departureDateTime;

    @Column(name = "arrival_date_time")
    private OffsetDateTime arrivalDateTime;

    @Column(name = "price")
    private double price;

}
