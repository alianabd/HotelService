package com.maf.core;

import com.maf.TestBase;
import com.maf.core.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;

public class HotelBusinessTest extends TestBase {

    @Test
    public void TestISOToDateGTFromDate() {

        Assertions.assertThrows(BusinessException.class, () -> {
            HotelBusiness.validateIsoDate(LocalDate.now().toString(), LocalDate.now().minusDays(2).toString());
        });
    }

    @Test
    public void TestInvalidISODateFormat() {

        Assertions.assertThrows(BusinessException.class, () -> {
            HotelBusiness.validateIsoDate("aaa", "bbb");
        });
    }

    @Test
    public void TestInstantToDateGTFromDate() {

        Assertions.assertThrows(BusinessException.class, () -> {
            HotelBusiness.validateInstantDate(Instant.now().toString(), Instant.now().minusSeconds(60000).toString());
        });
    }

    @Test
    public void TestInvalidInstantDateFormat() {

        Assertions.assertThrows(BusinessException.class, () -> {
            HotelBusiness.validateInstantDate("aaa", "bbb");
        });
    }
}
