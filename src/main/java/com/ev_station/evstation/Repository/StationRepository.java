package com.ev_station.evstation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev_station.evstation.Model.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {

}

