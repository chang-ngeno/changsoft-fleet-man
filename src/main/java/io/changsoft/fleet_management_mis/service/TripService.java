package io.changsoft.fleet_management_mis.service;

import io.changsoft.fleet_management_mis.domain.SystemParameter;
import io.changsoft.fleet_management_mis.domain.Trip;
import io.changsoft.fleet_management_mis.model.TripDTO;
import io.changsoft.fleet_management_mis.repos.SystemParameterRepository;
import io.changsoft.fleet_management_mis.repos.TripRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TripService {

    private final TripRepository tripRepository;
    private final SystemParameterRepository systemParameterRepository;

    public TripService(final TripRepository tripRepository,
            final SystemParameterRepository systemParameterRepository) {
        this.tripRepository = tripRepository;
        this.systemParameterRepository = systemParameterRepository;
    }

    public List<TripDTO> findAll() {
        return tripRepository.findAll()
                .stream()
                .map(trip -> mapToDTO(trip, new TripDTO()))
                .collect(Collectors.toList());
    }

    public TripDTO get(final Long id) {
        return tripRepository.findById(id)
                .map(trip -> mapToDTO(trip, new TripDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final TripDTO tripDTO) {
        final Trip trip = new Trip();
        mapToEntity(tripDTO, trip);
        return tripRepository.save(trip).getId();
    }

    public void update(final Long id, final TripDTO tripDTO) {
        final Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(tripDTO, trip);
        tripRepository.save(trip);
    }

    public void delete(final Long id) {
        tripRepository.deleteById(id);
    }

    private TripDTO mapToDTO(final Trip trip, final TripDTO tripDTO) {
        tripDTO.setId(trip.getId());
        tripDTO.setDeliveryNumber(trip.getDeliveryNumber());
        tripDTO.setTripDate(trip.getTripDate());
        tripDTO.setQuantity(trip.getQuantity());
        tripDTO.setUnitPrice(trip.getUnitPrice());
        tripDTO.setOrigin(trip.getOrigin());
        tripDTO.setDestination(trip.getDestination());
        tripDTO.setDistance(trip.getDistance());
        tripDTO.setEstimatedFuelConsumption(trip.getEstimatedFuelConsumption());
        tripDTO.setActualFuelConsumption(trip.getActualFuelConsumption());
        tripDTO.setBreakages(trip.getBreakages());
        tripDTO.setShortages(trip.getShortages());
        tripDTO.setFuelType(trip.getFuelType() == null ? null : trip.getFuelType().getId());
        return tripDTO;
    }

    private Trip mapToEntity(final TripDTO tripDTO, final Trip trip) {
        trip.setDeliveryNumber(tripDTO.getDeliveryNumber());
        trip.setTripDate(tripDTO.getTripDate());
        trip.setQuantity(tripDTO.getQuantity());
        trip.setUnitPrice(tripDTO.getUnitPrice());
        trip.setOrigin(tripDTO.getOrigin());
        trip.setDestination(tripDTO.getDestination());
        trip.setDistance(tripDTO.getDistance());
        trip.setEstimatedFuelConsumption(tripDTO.getEstimatedFuelConsumption());
        trip.setActualFuelConsumption(tripDTO.getActualFuelConsumption());
        trip.setBreakages(tripDTO.getBreakages());
        trip.setShortages(tripDTO.getShortages());
        if (tripDTO.getFuelType() != null && (trip.getFuelType() == null || !trip.getFuelType().getId().equals(tripDTO.getFuelType()))) {
            final SystemParameter fuelType = systemParameterRepository.findById(tripDTO.getFuelType())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "fuelType not found"));
            trip.setFuelType(fuelType);
        }
        return trip;
    }

}
