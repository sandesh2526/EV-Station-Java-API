package com.ev_station.evstation.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Station {
    /*
     * @GeneratedValue(generator = "uuid")
     * 
     * @GenericGenerator(name = "uuid", strategy = "uuid2")
     */ 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID as Primary Key
    private Integer stationId;
    private String stationName;
    private Integer stationPricing;
    private String stationAddress;
}