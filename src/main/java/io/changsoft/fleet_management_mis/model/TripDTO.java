package io.changsoft.fleet_management_mis.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class TripDTO {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String deliveryNumber;

    @NotNull
    private LocalDate tripDate;

    @NotNull
    private Long quantity;

    @NotNull
    private Double unitPrice;

    @NotNull
    @Size(max = 150)
    private String origin;

    @NotNull
    @Size(max = 150)
    private String destination;

    @NotNull
    private Double distance;

    @NotNull
    private Double estimatedFuelConsumption;

    @NotNull
    private Double actualFuelConsumption;

    @NotNull
    private Double breakages;

    @NotNull
    private Double shortages;

    @NotNull
    private Long fuelType;

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

    public Long getFuelType() {
        return fuelType;
    }

    public void setFuelType(final Long fuelType) {
        this.fuelType = fuelType;
    }

}
