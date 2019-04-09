package com.maf.resources;

import com.maf.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * Test class integration test with hotels resource
 *
 * @author abduallah
 */
public class HotelsResourceTest extends TestBase {

    @Test
    public void TestGetBestHotels() {

        Assertions.assertTimeout(Duration.ofSeconds(60), () -> {
            List bestHotels =
                    dropwizardExtension.client().target("http://localhost:" +
                            dropwizardExtension.getLocalPort() + "/hotels/BestHotels?fromDate=" + LocalDate.now().toString() +
                            "&toDate=" + LocalDate.now().plusDays(1) + "&city=AMM")
                            .request(MediaType.APPLICATION_JSON).get(List.class);

            Assertions.assertNotNull(bestHotels);
            Assertions.assertTrue(bestHotels.size() == 3);
        });
    }

    @Test
    public void TestGetCrazyHotels() {

        Assertions.assertTimeout(Duration.ofSeconds(60), () -> {
            List crazyHotels =
                    dropwizardExtension.client().target("http://localhost:" +
                            dropwizardExtension.getLocalPort() + "/hotels/CrazyHotels?from=" + Instant.now().toString() +
                            "&to=" + Instant.now().plusSeconds(60000) + "&city=AMM")
                            .request(MediaType.APPLICATION_JSON).get(List.class);

            Assertions.assertNotNull(crazyHotels);
            Assertions.assertTrue(crazyHotels.size() == 3);
        });
    }

    @Test
    public void TestGetAvailableHotels() {

        Assertions.assertTimeout(Duration.ofSeconds(60), () -> {
            List availableHotels =
                    dropwizardExtension.client().target("http://localhost:" +
                            dropwizardExtension.getLocalPort() + "/hotels/AvailableHotels?fromDate=" + LocalDate.now().toString() +
                            "&toDate=" + LocalDate.now().plusDays(1) + "&city=AMM")
                            .request(MediaType.APPLICATION_JSON).get(List.class);

            Assertions.assertNotNull(availableHotels);
            Assertions.assertTrue(availableHotels.size() == 6);
        });
    }
}
