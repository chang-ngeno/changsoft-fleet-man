package io.changsoft.fleet_management_mis.rest;

import io.changsoft.fleet_management_mis.model.TripDTO;
import io.changsoft.fleet_management_mis.service.TripService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/trips", produces = MediaType.APPLICATION_JSON_VALUE)
public class TripController {

    private final TripService tripService;

    public TripController(final TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        return ResponseEntity.ok(tripService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTrip(@PathVariable final Long id) {
        return ResponseEntity.ok(tripService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createTrip(@RequestBody @Valid final TripDTO tripDTO) {
        return new ResponseEntity<>(tripService.create(tripDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrip(@PathVariable final Long id,
            @RequestBody @Valid final TripDTO tripDTO) {
        tripService.update(id, tripDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable final Long id) {
        tripService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
