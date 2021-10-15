package io.changsoft.fleet_management_mis.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 30)
    private String productCode;

    @NotNull
    @Size(max = 100)
    private String productName;

    @NotNull
    @Size(max = 100)
    private String unitOfMeasurement;

    @NotNull
    private Double unitPrice;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(final String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(final String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
