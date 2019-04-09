package com.maf;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;
import java.util.List;

public class HotelServiceConfiguration extends Configuration {

    @JsonProperty("hotels_providers")
    @NotNull
    private List<String> hotelsProviders;

    public List<String> getHotelsProviders() {
        return hotelsProviders;
    }

    public void setHotelsProviders(List<String> hotelsProviders) {
        this.hotelsProviders = hotelsProviders;
    }
}
