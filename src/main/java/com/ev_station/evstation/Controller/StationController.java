package com.ev_station.evstation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ev_station.evstation.Model.Station;
import com.ev_station.evstation.Repository.StationRepository;
import com.ev_station.evstation.Service.StationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class StationController {

    @Autowired
    StationService stationService;

    // Get Mappings
    /*
     * This method is mapped to the base URL and we return a list of the stations
     * when we get the request for this url
     * localhost:8080/
     */
    @GetMapping("/")
    ResponseEntity<List<Station>> defaultView() {

        System.out.println("[StationController] (defaultView) ");
        
        return ResponseEntity.ok(stationService.getAll());
    }

    /*
     * To return the specific number of stations, where the number of station is
     * provided as
     * a rquest parameters i.e. ?limit=numberOfStations
     */
    @GetMapping(value = "/", params = { "limit" })
    List<Station> getLimitedStations(@RequestParam("limit") Integer count) {
        List<Station> stationsToReturn = null;
        if (count > 0) {
            log.trace("Returning the list of stations currently available inside database");
            System.out.println("Here Inside the limiter");
            return stationService.getAll().subList(0, count);
        }
        /*
         * if(order != null && parameter != null) {
         * return stationService.getSortedStations(order, parameter);
         * }
         */ return stationsToReturn;
    }

    @GetMapping(value = "/", params = { "sort", "parameter" })
    List<Station> getLimitedStations(@RequestParam(name ="sort",required = false) String sort, @RequestParam String parameter) {
        List<Station> stationsToReturn = null;
        if (sort != null && parameter != null) {
            log.trace("Returning the list of stations currently available inside database");
            System.out.println("Here Inside the limiter " + sort + " " + parameter);
            return stationService.getSortedStations(sort, parameter);
        }
        /*
         * if(order != null && parameter != null) {
         * return stationService.getSortedStations(order, parameter);
         * }
         */ 
        return stationsToReturn;
    }

    /*
     * TO get a station we enter the station id as a path variable to the '/show/'
     * URL
     */
    @GetMapping("/show/{id}")
    Station getStationById(@PathVariable Integer id) {
        log.trace("Return the station with the given id");
        System.out.println("[getStationById]");
        return stationService.getById(id);
    }

    /*
     * For post request on the base url we add a station in database
    */

    @PostMapping("/")
    void addNewStation(@RequestBody Station station) {
        log.trace("New Station is added");
        System.out.println("[StationController] (addNewStation)");
        stationService.addStation(station);
    }

    /*
     * to edit a station we pass the station id as a path variable and
     * station information as the request body
     * We can use the station id to update the station in database
     * For e.g. if we have a station with station_id as 1 and we want to update it,
     * so we will provide the other station parameters and then we change id of that
     * station object and set id to the given id and
     * then use the @see addNewStation to add new id
     */

    @PutMapping("/{id}/edit")
    ResponseEntity<Station> editExistingStation(@PathVariable Integer id, @RequestBody Station station) {
        if (stationService.getById(id).equals(null)) {
            System.out.println("Null ID FOUNstationD");
            return new ResponseEntity<Station>(HttpStatus.NOT_FOUND);
        }
        System.out.println("ID IS : " + id + " Station is: " + station.getStationId());
        log.trace("Editing the existing ");
        station.setStationId(id);
        System.out.println("ID IS : " + id + " Station is: " + station);
        stationService.addStation(station);
        return new ResponseEntity<Station>(HttpStatus.OK);
    }

    /*
     * The method will delete the station for the specified id in Path
     */
    @DeleteMapping("/delete/{id}")
    void deleteStation(@PathVariable Integer id) {
        log.trace("Deleting the station with id: [{}]", id);
        stationService.deleteStation(id);
    }
}