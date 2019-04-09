package com.maf.resources;

import com.maf.HotelServiceConfiguration;
import com.maf.api.ServerResponse;
import com.maf.api.hotel.BestHotel;
import com.maf.api.hotel.CityIATACode;
import com.maf.api.hotel.CrazyHotel;
import com.maf.api.hotel.Hotel;
import com.maf.core.HotelBusiness;
import com.maf.core.exception.BusinessException;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class represent the URI endpoints for hotels data
 *
 * @author abduallah
 */
@Path("/hotels")
public class HotelsResource {

    private final HotelServiceConfiguration hotelServiceConfiguration;

    public HotelsResource(HotelServiceConfiguration configuration) {
        hotelServiceConfiguration = configuration;
    }

    /**
     * GET Method to return List of available providers hotels
     *
     * @param fromDate       , required parameter <code>{@link String}</code> from date with ISO_LOCAL_DATE format
     * @param toDate         , required parameter <code>{@link String}</code> to date with ISO_LOCAL_DATE format
     * @param cityIATACode   , required parameter one of the cities IATA codes
     * @param numberOfAdults , not required parameter with 1 as default value indicates the number of adults people in the room
     * @return <code>{@link Response}</code>
     * contains <code>{@link Set}</code> of <code>{@link com.maf.api.hotel.Hotel}</code> sorted by rate
     */
    @GET
    @Path("/AvailableHotels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableHotels(@QueryParam("fromDate") @NotEmpty String fromDate,
                                       @QueryParam("toDate") @NotEmpty String toDate,
                                       @QueryParam("city") @NotNull CityIATACode cityIATACode,
                                       @QueryParam("numberOfAdults") @DefaultValue("1") Integer numberOfAdults) {


        Set<Hotel> hotels;
        try {
            HotelBusiness.validateIsoDate(fromDate, toDate);
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("fromDate", fromDate);
            urlParams.put("toDate", toDate);
            urlParams.put("city", cityIATACode.name());
            urlParams.put("numberOfAdults", numberOfAdults.toString());
            hotels = HotelBusiness.generateAvailableHotels(hotelServiceConfiguration.getHotelsProviders(), urlParams);
        } catch (BusinessException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(e.getMessage())).build();
        } catch (IOException e) {
            return Response.serverError().entity(new ServerResponse(e.getMessage())).build();
        }

        return Response.ok(hotels).build();
    }


    /**
     * GET Method to return List of available best hotels
     *
     * @param fromDate       , required parameter <code>{@link String}</code> from date with ISO_LOCAL_DATE format
     * @param toDate         , required parameter <code>{@link String}</code> to date with ISO_LOCAL_DATE format
     * @param cityIATACode   , required parameter one of the cities IATA codes
     * @param numberOfAdults , not required parameter with 1 as default value indicates the number of adults people in the room
     * @return <code>{@link Response}</code>
     * contains <code>{@link List}</code> of <code>{@link com.maf.api.hotel.BestHotel}</code> sorted by rate
     */
    @GET
    @Path("/BestHotels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestHotels(@QueryParam("fromDate") @NotEmpty String fromDate,
                                  @QueryParam("toDate") @NotEmpty String toDate,
                                  @QueryParam("city") @NotNull CityIATACode cityIATACode,
                                  @QueryParam("numberOfAdults") @NotNull @DefaultValue("1") Integer numberOfAdults) {

        List<BestHotel> bestHotels;

        try {
            HotelBusiness.validateIsoDate(fromDate, toDate);
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("fromDate", fromDate);
            urlParams.put("toDate", toDate);
            urlParams.put("city", cityIATACode.name());
            urlParams.put("numberOfAdults", numberOfAdults.toString());
            bestHotels = HotelBusiness.generateBestHotels(hotelServiceConfiguration.getHotelsProviders().get(0), urlParams);
        } catch (BusinessException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(e.getMessage())).build();
        } catch (IOException e) {
            return Response.serverError().entity(new ServerResponse(e.getMessage())).build();
        }
        return Response.ok(bestHotels).build();
    }


    /**
     * GET Method to return List of available crazy hotels
     *
     * @param fromDate       , required parameter <code>{@link String}</code> from date with ISO_INSTANT format
     * @param toDate         , required parameter <code>{@link String}</code> to date with ISO_INSTANT format
     * @param cityIATACode   , required parameter one of the cities IATA codes
     * @param numberOfAdults , not required parameter with 1 as default value indicates the number of adults people in the room
     * @return <code>{@link Response}</code>
     * contains <code>{@link List}</code> of <code>{@link com.maf.api.hotel.CrazyHotel}</code> sorted by rate
     */
    @GET
    @Path("/CrazyHotels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCrazyHotels(@QueryParam("from") @NotEmpty String fromDate,
                                   @QueryParam("to") @NotEmpty String toDate,
                                   @QueryParam("city") @NotNull CityIATACode cityIATACode,
                                   @QueryParam("adultsCount") @DefaultValue("1") Integer numberOfAdults) {


        List<CrazyHotel> crazyHotels;
        try {
            HotelBusiness.validateInstantDate(fromDate, toDate);
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("fromDate", fromDate);
            urlParams.put("toDate", toDate);
            urlParams.put("city", cityIATACode.name());
            urlParams.put("numberOfAdults", numberOfAdults.toString());
            crazyHotels = HotelBusiness.generateCrazyHotels(hotelServiceConfiguration.getHotelsProviders().get(1), urlParams);
        } catch (BusinessException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(e.getMessage())).build();
        } catch (IOException e) {
            return Response.serverError().entity(new ServerResponse(e.getMessage())).build();
        }

        return Response.ok(crazyHotels).build();
    }
}
