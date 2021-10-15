package io.changsoft.fleet_management_mis.repos;

import io.changsoft.fleet_management_mis.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
