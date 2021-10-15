package io.changsoft.fleet_management_mis.service;

import io.changsoft.fleet_management_mis.domain.Trip;
import io.changsoft.fleet_management_mis.domain.TripDetail;
import io.changsoft.fleet_management_mis.model.TripDetailDTO;
import io.changsoft.fleet_management_mis.repos.TripDetailRepository;
import io.changsoft.fleet_management_mis.repos.TripRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TripDetailService {

    private final TripDetailRepository tripDetailRepository;
    private final TripRepository tripRepository;

    public TripDetailService(final TripDetailRepository tripDetailRepository,
            final TripRepository tripRepository) {
        this.tripDetailRepository = tripDetailRepository;
        this.tripRepository = tripRepository;
    }

    public List<TripDetailDTO> findAll() {
        return tripDetailRepository.findAll()
                .stream()
                .map(tripDetail -> mapToDTO(tripDetail, new TripDetailDTO()))
                .collect(Collectors.toList());
    }

    public TripDetailDTO get(final Long id) {
        return tripDetailRepository.findById(id)
                .map(tripDetail -> mapToDTO(tripDetail, new TripDetailDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final TripDetailDTO tripDetailDTO) {
        final TripDetail tripDetail = new TripDetail();
        mapToEntity(tripDetailDTO, tripDetail);
        return tripDetailRepository.save(tripDetail).getId();
    }

    public void update(final Long id, final TripDetailDTO tripDetailDTO) {
        final TripDetail tripDetail = tripDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(tripDetailDTO, tripDetail);
        tripDetailRepository.save(tripDetail);
    }

    public void delete(final Long id) {
        tripDetailRepository.deleteById(id);
    }

    private TripDetailDTO mapToDTO(final TripDetail tripDetail, final TripDetailDTO tripDetailDTO) {
        tripDetailDTO.setId(tripDetail.getId());
        tripDetailDTO.setParticulars(tripDetail.getParticulars());
        tripDetailDTO.setUnitPrice(tripDetail.getUnitPrice());
        tripDetailDTO.setQuantity(tripDetail.getQuantity());
        tripDetailDTO.setDetailType(tripDetail.getDetailType());
        tripDetailDTO.setTrip(tripDetail.getTrip() == null ? null : tripDetail.getTrip().getId());
        return tripDetailDTO;
    }

    private TripDetail mapToEntity(final TripDetailDTO tripDetailDTO, final TripDetail tripDetail) {
        tripDetail.setParticulars(tripDetailDTO.getParticulars());
        tripDetail.setUnitPrice(tripDetailDTO.getUnitPrice());
        tripDetail.setQuantity(tripDetailDTO.getQuantity());
        tripDetail.setDetailType(tripDetailDTO.getDetailType());
        if (tripDetailDTO.getTrip() != null && (tripDetail.getTrip() == null || !tripDetail.getTrip().getId().equals(tripDetailDTO.getTrip()))) {
            final Trip trip = tripRepository.findById(tripDetailDTO.getTrip())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "trip not found"));
            tripDetail.setTrip(trip);
        }
        return tripDetail;
    }

}
