package com.maf.core;

import com.google.gson.Gson;
import com.maf.api.hotel.BestHotel;
import com.maf.api.hotel.CrazyHotel;
import com.maf.api.hotel.Hotel;
import com.maf.core.exception.BusinessException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Class to handle business operations like date formats and generate dummy date
 *
 * @author abduallah
 */
public class HotelBusiness {

    private static OkHttpClient client = new OkHttpClient();
    private static Gson gson = new Gson();

    /**
     * Method to validate that the passed date are on iso local date format and from date is less than to date
     *
     * @param fromDate , <code>{@link String}</code> from date with ISO_LOCAL_DATE format
     * @param toDate   , <code>{@link String}</code> to date with ISO_LOCAL_DATE format
     * @throws BusinessException In case of invalid date format or from date gt to date
     * @since V1.0
     */
    public static void validateIsoDate(String fromDate, String toDate) throws BusinessException {

        try {

            LocalDate fromDateConverted = LocalDate.parse(fromDate, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate toDateConverted = LocalDate.parse(toDate, DateTimeFormatter.ISO_LOCAL_DATE);
            if (toDateConverted.isBefore(fromDateConverted)) {
                throw new BusinessException("to date should be greater than from date");
            }
        } catch (RuntimeException ex) {
            throw new BusinessException("Invalid date format format should be yyyy-MM-dd");
        }
    }

    /**
     * Method to validate that the passed date are on iso instant date format and from date is less than to date
     *
     * @param fromDate , <code>{@link String}</code> from date with ISO_INSTANT format
     * @param toDate   , <code>{@link String}</code> to date with ISO_INSTANT format
     * @throws BusinessException In case of invalid date format or from date gt to date
     * @since V1.0
     */
    public static void validateInstantDate(String fromDate, String toDate) throws BusinessException {

        try {
            Instant fromDateConverted = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(fromDate));
            Instant toDateConverted = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(toDate));
            if (toDateConverted.isBefore(fromDateConverted)) {
                throw new BusinessException("to date should be greater than from date");
            }
        } catch (RuntimeException ex) {
            throw new BusinessException("Invalid date format  should be yyyy-MM-ddThh:mm:ssZ");
        }
    }

    /**
     * Method to send GET request to external API
     *
     * @param url       , <code>{@link String}</code> requested URL
     * @param urlParams , <code>{@link Map}</code> of param key and value
     * @return <code>{@link Response}</code>
     * @throws IOException In case of error
     */
    public static Response getRequestResponse(String url, Map<String, String> urlParams) throws IOException {

        StringBuilder urlBuilder = new StringBuilder(url);
        if (urlParams != null && !url.isEmpty()) {
            urlBuilder.append("?");
            urlParams.forEach((k, v) -> {
                urlBuilder.append(k);
                urlBuilder.append("=");
                urlBuilder.append(v);
                urlBuilder.append("&");
            });
        }

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .get()
                .build();

        return client.newCall(request).execute();
    }

    /**
     * Method to send external API call to fetch list of best hotels
     *
     * @param url       , <code>{@link String}</code> best hotels url
     * @param urlParams , <code>{@link Map}</code> best urls query param, param name as key and param value
     * @return <code>{@link List}</code> of <code>{@link BestHotel}</code>
     * @throws IOException       , In case of error
     * @throws BusinessException , In case of API call failure
     */
    public static List<BestHotel> generateBestHotels(String url, Map<String, String> urlParams) throws IOException, BusinessException {

        List<BestHotel> bestHotels;
        Response response = getRequestResponse(url, urlParams);

        if (response.isSuccessful()) {
            // bestHotels = gson.fromJson(response.body().string(), List.class);//just to make sure that we are converting from json response
            bestHotels = new ArrayList<>();
            bestHotels.add(new BestHotel("Best3", 3, 50.0123f, "smoking"));
            bestHotels.add(new BestHotel("Best4", 4, 70.01234f, "sea view"));
            bestHotels.add(new BestHotel("Best5", 5, 100.0f, "smoking,sea view"));
        } else {
            throw new BusinessException("Failed to call best hotels API, please try again later");
        }
        return bestHotels;
    }

    /**
     * Method to send external API call to fetch list of crazy hotels
     *
     * @param url       , <code>{@link String}</code> best hotels url
     * @param urlParams , <code>{@link Map}</code> best urls query param, param name as key and param value
     * @return <code>{@link List}</code> of <code>{@link CrazyHotel}</code>
     * @throws IOException       , In case of error
     * @throws BusinessException , In case of API call failure
     */
    public static List<CrazyHotel> generateCrazyHotels(String url, Map<String, String> urlParams) throws IOException, BusinessException {

        List<CrazyHotel> crazyHotels;
        Response response = getRequestResponse(url, urlParams);

        if (response.isSuccessful()) {
            // crazyHotels = gson.fromJson(response.body().string(), List.class);//just to make sure that we are converting from json response
            crazyHotels = new ArrayList<>();
            crazyHotels.add(new CrazyHotel("Crazy1", "*", 40.0f, 0.5F, new String[]{"garden view"}));
            crazyHotels.add(new CrazyHotel("Crazy2", "**", 45.0f, 0.0f, new String[]{"king size bed"}));
            crazyHotels.add(new CrazyHotel("Crazy3", "***", 50.0f, 0.3f, new String[]{"queen bed"}));
        } else {
            throw new BusinessException("Failed to call crazy hotels API, please try again later");
        }
        return crazyHotels;
    }

    /**
     * Method to get all hotels providers data
     *
     * @param hotelsProviders , <code>{@link List}</code> of hotels providers urls
     * @param urlParams       , <code>{@link Map}</code> urls query param, param name as key and param value
     * @return <code>{@link Set}</code> of <code>{@link Hotel}</code> ordered by hotel rate descending
     * @throws IOException       , In case of error
     * @throws BusinessException , In case of API call failure
     */
    public static Set<Hotel> generateAvailableHotels(List<String> hotelsProviders, Map<String, String> urlParams) throws IOException, BusinessException {

        List<BestHotel> bestHotels = generateBestHotels(hotelsProviders.get(0), urlParams);
        List<CrazyHotel> crazyHotels = generateCrazyHotels(hotelsProviders.get(1), urlParams);

        Set<Hotel> hotels = new TreeSet<>();

        bestHotels.forEach(hotel -> {
            hotels.add(new Hotel("BestHotels", hotel.getHotel(), hotel.getHotelFare(), hotel.getRoomAmenities().split(","), hotel.getHotelRate()));
        });

        crazyHotels.forEach(hotel -> {
            hotels.add(new Hotel("CrazyHotels", hotel.getHotelName(), hotel.getPrice(), hotel.getAmenities(), hotel.getRate().length()));
        });

        return hotels;
    }
}
