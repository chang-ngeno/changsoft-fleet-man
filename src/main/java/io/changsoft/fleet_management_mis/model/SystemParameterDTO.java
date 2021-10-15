package io.changsoft.fleet_management_mis.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SystemParameterDTO {

    private Long id;

    @NotNull
    @Size(max = 30)
    private String paramCode;

    @NotNull
    @Size(max = 150)
    private String paramName;

    @NotNull
    private String description;

    private Long parent;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(final String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(final String paramName) {
        this.paramName = paramName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(final Long parent) {
        this.parent = parent;
    }

}
