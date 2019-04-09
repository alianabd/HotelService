package com.maf.api.hotel;

public class Hotel implements Comparable<Hotel> {

    private String providerName;
    private String hotelName;
    private Float fare;
    private String[] amenities;
    private Integer rate;

    public Hotel() {
    }

    public Hotel(String providerName, String hotelName, Float fare, String[] amenities, Integer rate) {
        this.providerName = providerName;
        this.hotelName = hotelName;
        this.fare = fare;
        this.amenities = amenities;
        this.rate = rate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }

    public String[] getAmenities() {
        return amenities;
    }

    public void setAmenities(String[] amenities) {
        this.amenities = amenities;
    }

    @Override
    public int compareTo(Hotel o) {

        int rateDif = this.rate - o.rate;
        if (rateDif != 0) {
            return rateDif;
        }

        if (this.getFare() - o.getFare() != 0) {
            return -1;
        }

        if (!this.getProviderName().equalsIgnoreCase(o.getProviderName())) {
            return 1;
        } else {
            return -1;
        }
    }
}
