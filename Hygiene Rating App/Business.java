package com.example.week20;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.Date;

public class Business {
    private int id;
    private String businessName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String postCode;
    private int ratingValue;
    private Date ratingDate;
    private double latitude;
    private double longitude;
    private double location;

    public void setId(int id) {this.id=id;}
    public int getId() {return id;}
    public void setBusinessName(String businessName) {this.businessName = businessName;}
    public String getBusinessName() {return businessName;}

    public void setAddressLine1(String addressLine1) {this.addressLine1 = addressLine1;}
    public String getAddressLine1() {return addressLine1;}

    public void setAddressLine2(String addressLine2) {this.addressLine2 = addressLine2;}
    public String getAddressLine2() {return addressLine2;}

    public void setAddressLine3(String addressLine3) {this.addressLine3 = addressLine3;}
    public String getAddressLine3() {return addressLine3;}

    public void setPostCode(String postCode) {this.postCode = postCode;}
    public String getPostCode() {return postCode;}

    public void setRatingValue(int ratingValue) {this.ratingValue = ratingValue;}
    public int getRatingValue() {return ratingValue;}

    public void setRatingDate(Date ratingDate) {this.ratingDate = ratingDate;}
    public Date getRatingDate() {return ratingDate;}


    public void setLatitude(double latitude) {this.latitude = latitude;}
    public double getLatitude() {return latitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}
    public double getLongitude() {return longitude;}

    public void setLocation(double location) {this.location = location;}
    public double getLocation() {return location;}
}
