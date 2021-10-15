package io.changsoft.fleet_management_mis.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class TripDetailDTO {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String particulars;

    @NotNull
    private Double unitPrice;

    @NotNull
    private Double quantity;

    @NotNull
    private TripDetailType detailType;

    private Long trip;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(final String particulars) {
        this.particulars = particulars;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(final Double quantity) {
        this.quantity = quantity;
    }

    public TripDetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(final TripDetailType detailType) {
        this.detailType = detailType;
    }

    public Long getTrip() {
        return trip;
    }

    public void setTrip(final Long trip) {
        this.trip = trip;
    }

}
