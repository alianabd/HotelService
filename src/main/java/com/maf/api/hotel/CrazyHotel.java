package com.maf.api.hotel;

public class CrazyHotel {

    private String hotelName;
    private String rate;
    private Float price;
    private Float discount;
    private String [] amenities;

    public CrazyHotel() {
    }

    public CrazyHotel(String hotelName, String rate, Float price, Float discount, String[] amenities) {
        this.hotelName = hotelName;
        this.rate = rate;
        this.price = price;
        this.discount = discount;
        this.amenities = amenities;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String[] getAmenities() {
        return amenities;
    }

    public void setAmenities(String[] amenities) {
        this.amenities = amenities;
    }
}
