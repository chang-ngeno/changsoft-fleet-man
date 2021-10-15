package io.changsoft.fleet_management_mis.service;

import io.changsoft.fleet_management_mis.domain.Vehicle;
import io.changsoft.fleet_management_mis.model.VehicleDTO;
import io.changsoft.fleet_management_mis.repos.VehicleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicle -> mapToDTO(vehicle, new VehicleDTO()))
                .collect(Collectors.toList());
    }

    public VehicleDTO get(final Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> mapToDTO(vehicle, new VehicleDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final VehicleDTO vehicleDTO) {
        final Vehicle vehicle = new Vehicle();
        mapToEntity(vehicleDTO, vehicle);
        return vehicleRepository.save(vehicle).getId();
    }

    public void update(final Long id, final VehicleDTO vehicleDTO) {
        final Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(vehicleDTO, vehicle);
        vehicleRepository.save(vehicle);
    }

    public void delete(final Long id) {
        vehicleRepository.deleteById(id);
    }

    private VehicleDTO mapToDTO(final Vehicle vehicle, final VehicleDTO vehicleDTO) {
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setMake(vehicle.getMake());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setRegistrationNumber(vehicle.getRegistrationNumber());
        vehicleDTO.setYearOfManufacture(vehicle.getYearOfManufacture());
        vehicleDTO.setMileageLastService(vehicle.getMileageLastService());
        vehicleDTO.setLastServiceDate(vehicle.getLastServiceDate());
        vehicleDTO.setLastServiceStation(vehicle.getLastServiceStation());
        return vehicleDTO;
    }

    private Vehicle mapToEntity(final VehicleDTO vehicleDTO, final Vehicle vehicle) {
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        vehicle.setYearOfManufacture(vehicleDTO.getYearOfManufacture());
        vehicle.setMileageLastService(vehicleDTO.getMileageLastService());
        vehicle.setLastServiceDate(vehicleDTO.getLastServiceDate());
        vehicle.setLastServiceStation(vehicleDTO.getLastServiceStation());
        return vehicle;
    }

}
