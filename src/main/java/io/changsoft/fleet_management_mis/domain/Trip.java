package io.changsoft.fleet_management_mis.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;


@Entity
public class Trip {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String deliveryNumber;

    @Column(nullable = false)
    private LocalDate tripDate;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false, length = 150)
    private String origin;

    @Column(nullable = false, length = 150)
    private String destination;

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private Double estimatedFuelConsumption;

    @Column(nullable = false)
    private Double actualFuelConsumption;

    @Column(nullable = false)
    private Double breakages;

    @Column(nullable = false)
    private Double shortages;

    @OneToMany(mappedBy = "trip")
    private Set<TripDetail> tripTripDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_type_id", nullable = false)
    private SystemParameter fuelType;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(final String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(final LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(final Double distance) {
        this.distance = distance;
    }

    public Double getEstimatedFuelConsumption() {
        return estimatedFuelConsumption;
    }

    public void setEstimatedFuelConsumption(final Double estimatedFuelConsumption) {
        this.estimatedFuelConsumption = estimatedFuelConsumption;
    }

    public Double getActualFuelConsumption() {
        return actualFuelConsumption;
    }

    public void setActualFuelConsumption(final Double actualFuelConsumption) {
        this.actualFuelConsumption = actualFuelConsumption;
    }

    public Double getBreakages() {
        return breakages;
    }

    public void setBreakages(final Double breakages) {
        this.breakages = breakages;
    }

    public Double getShortages() {
        return shortages;
    }

    public void setShortages(final Double shortages) {
        this.shortages = shortages;
    }

    public Set<TripDetail> getTripTripDetails() {
        return tripTripDetails;
    }

    public void setTripTripDetails(final Set<TripDetail> tripTripDetails) {
        this.tripTripDetails = tripTripDetails;
    }

    public SystemParameter getFuelType() {
        return fuelType;
    }

    public void setFuelType(final SystemParameter fuelType) {
        this.fuelType = fuelType;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
