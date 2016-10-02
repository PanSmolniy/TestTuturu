package com.smolianinov.app.testtuturu.json_processor;


import java.util.List;

public class City {

    private String countryName;
    private String district;
    private int id;
    private String cityName;
    private String region;
    private List<Station> stations;

    public City(String countryName, String district, int id, String cityName, String region, List<Station> stations) {
        this.countryName = countryName;
        this.district = district;
        this.id = id;
        this.cityName = cityName;
        this.region = region;
        this.stations = stations;
    }

    public String getDistrict() {
        return district;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getRegion() {
        return region;
    }

    public List<Station> getStations() {
        return stations;
    }

    public String getCountryName() {
        return countryName;
    }
}
