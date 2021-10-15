package io.changsoft.fleet_management_mis.repos;

import io.changsoft.fleet_management_mis.domain.TripDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TripDetailRepository extends JpaRepository<TripDetail, Long> {
}
