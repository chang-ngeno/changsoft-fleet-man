package io.changsoft.fleet_management_mis.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class VehicleDTO {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String make;

    @NotNull
    @Size(max = 100)
    private String model;

    @NotNull
    @Size(max = 30)
    private String registrationNumber;

    @Size(max = 10)
    private String yearOfManufacture;

    private Long mileageLastService;

    private LocalDate lastServiceDate;

    @Size(max = 150)
    private String lastServiceStation;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(final String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(final String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(final String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Long getMileageLastService() {
        return mileageLastService;
    }

    public void setMileageLastService(final Long mileageLastService) {
        this.mileageLastService = mileageLastService;
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(final LocalDate lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public String getLastServiceStation() {
        return lastServiceStation;
    }

    public void setLastServiceStation(final String lastServiceStation) {
        this.lastServiceStation = lastServiceStation;
    }

}
