package com.maf.api.hotel;

import java.math.BigDecimal;

public class BestHotel {

    private String hotel;
    private Integer hotelRate;
    private Float hotelFare;
    private String roomAmenities;

    public BestHotel() {
    }

    public BestHotel(String hotel, Integer hotelRate, Float hotelFare, String roomAmenities) {
        this.hotel = hotel;
        this.hotelRate = hotelRate;
        setHotelFare(hotelFare);
        this.roomAmenities = roomAmenities;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Integer getHotelRate() {
        return hotelRate;
    }

    public void setHotelRate(Integer hotelRate) {
        this.hotelRate = hotelRate;
    }

    public Float getHotelFare() {
        return hotelFare;
    }

    public void setHotelFare(Float hotelFare) {
        this.hotelFare = new BigDecimal(hotelFare).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }

    public String getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(String roomAmenities) {
        this.roomAmenities = roomAmenities;
    }
}
