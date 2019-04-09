package com.maf;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.atomic.AtomicInteger;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;

public class TestBase {

    public static final DropwizardAppExtension<HotelServiceConfiguration> dropwizardExtension =
            new DropwizardAppExtension<>(
                    HotelServiceApplication.class, resourceFilePath("test-config.yml"),
                    ConfigOverride.config("server.applicationConnectors[0].port", "0"),
                    ConfigOverride.config("server.adminConnectors[0].port", "0"));
    private final static AtomicInteger beforeAllCallCount = new AtomicInteger(0);

    @BeforeAll
    public static void init() {
        dropwizardExtension.before();
        if (beforeAllCallCount.getAndIncrement() != 0) {
            return;
        }
    }
}
