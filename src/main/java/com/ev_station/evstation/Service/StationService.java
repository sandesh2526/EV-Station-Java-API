package com.ev_station.evstation.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ev_station.evstation.Model.Station;
import com.ev_station.evstation.Repository.StationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StationService {

    @Autowired
    StationRepository stationRepository;

    public List<Station> getAll() {
        log.info("Request for list of stations");
        return stationRepository.findAll();
    }

    public Station getById(Integer id) {
        log.trace("Request for a station");
        return stationRepository.findById(id).get();
    }

    public void addStation(Station station) {
        log.trace("Request to add new station");
        System.out.println("[StationService] (addStation)"+station);
        stationRepository.save(station);
    }

    public void deleteStation(Integer id) {
        log.trace("Request to delete station");
        stationRepository.deleteById(id);
    }

    public List<Station> getSortedStations(String sortOrder, String parameter) {
        System.out.println("Inside the sorting method");
        Comparator<Station> comparator = null;
        List<Station> sortedStations = stationRepository.findAll();

        try {
            System.out.println(this.getClass().getFields());
        } catch (Exception e) {
            System.out.println("This field does not exists");
            e.printStackTrace();
        }
        switch (parameter) {
            case "station_id":
                if (sortOrder.equals("desc")) {
                    comparator = (Station a, Station b) -> Integer.compare(b.getStationId(), a.getStationId());
                    System.out.println("[getSortedStation] {station_id -> descending}");
                } else {
                    comparator = (Station a, Station b) -> Integer.compare(a.getStationId(), b.getStationId());
                    System.out.println("[getSortedStation] {station_id -> ascending}");
                }
                break;
            case "station_pricing":
                if (sortOrder.equals("desc")) {
                    comparator = (Station a, Station b) -> Integer.compare(b.getStationPricing() , a.getStationPricing());
                    System.out.println("[getSortedStation] {station_pricing -> descending}");
                } else {
                    comparator = (Station a, Station b) -> Integer.compare(a.getStationPricing() , b.getStationPricing());
                    System.out.println("[getSortedStation] {station_pricing -> ascending}");
                }
                break;
            case "station_name":
                if (sortOrder.equals("asc")) {
                    comparator = (Station a, Station b) -> a.getStationName().compareTo(b.getStationName());
                    System.out.println("[getSortedStation] {station_name -> ascending}");
                } else {
                    comparator = (Station a, Station b) -> {
                        System.out.println(a.getStationName()+" "+b.getStationName()+" "+(0-a.getStationName().compareTo(b.getStationName())));
                        return (0 - (a.getStationName().compareTo(b.getStationName())));
                    }; 
                    System.out.println("[getSortedStation] {station_name -> descending}");
                }
                break;
            case "station_address":
                if (sortOrder.equals("asc")) {
                    comparator = (Station a, Station b) -> a.getStationAddress().compareTo(b.getStationAddress());
                    System.out.println("[getSortedStation] {station_address -> ascending}");
                } else {
                    comparator = (Station a, Station b) -> (0 - (a.getStationAddress().compareTo(b.getStationAddress())));
                    System.out.println("[getSortedStation] {station_address -> descending}");
                }
                break;
            default:
                System.out.println("[getSortedStation] {switch} (default)");
                break;
        }

        Collections.sort(sortedStations, comparator);
        return sortedStations;
    }
}