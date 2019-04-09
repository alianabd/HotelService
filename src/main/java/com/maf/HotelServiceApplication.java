package com.maf;

import com.maf.core.HotelBusiness;
import com.maf.resources.HotelsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HotelServiceApplication extends Application<HotelServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HotelServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "HotelService";
    }

    @Override
    public void initialize(final Bootstrap<HotelServiceConfiguration> bootstrap) {

    }

    @Override
    public void run(final HotelServiceConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(new HotelsResource(configuration));
    }
}
