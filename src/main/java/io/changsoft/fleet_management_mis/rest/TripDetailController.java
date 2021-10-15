package io.changsoft.fleet_management_mis.rest;

import io.changsoft.fleet_management_mis.model.TripDetailDTO;
import io.changsoft.fleet_management_mis.service.TripDetailService;
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
@RequestMapping(value = "/api/tripDetails", produces = MediaType.APPLICATION_JSON_VALUE)
public class TripDetailController {

    private final TripDetailService tripDetailService;

    public TripDetailController(final TripDetailService tripDetailService) {
        this.tripDetailService = tripDetailService;
    }

    @GetMapping
    public ResponseEntity<List<TripDetailDTO>> getAllTripDetails() {
        return ResponseEntity.ok(tripDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDetailDTO> getTripDetail(@PathVariable final Long id) {
        return ResponseEntity.ok(tripDetailService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createTripDetail(
            @RequestBody @Valid final TripDetailDTO tripDetailDTO) {
        return new ResponseEntity<>(tripDetailService.create(tripDetailDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTripDetail(@PathVariable final Long id,
            @RequestBody @Valid final TripDetailDTO tripDetailDTO) {
        tripDetailService.update(id, tripDetailDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTripDetail(@PathVariable final Long id) {
        tripDetailService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
