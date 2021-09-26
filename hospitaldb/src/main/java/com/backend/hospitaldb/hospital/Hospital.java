package com.backend.hospitaldb.hospital;

public class Hospital {

    private Long hospitalID;
    private String hospitalName;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public Hospital() {

    }

    public Hospital(Long hospitalID, String hospitalName, String street, String city, String state, String country, String postalCode) {
        this.hospitalID = hospitalID;
        this.hospitalName = hospitalName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Long getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(Long hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalID=" + hospitalID +
                ", hospitalName='" + hospitalName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }


}
