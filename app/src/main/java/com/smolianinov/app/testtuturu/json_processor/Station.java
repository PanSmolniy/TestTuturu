package com.smolianinov.app.testtuturu.json_processor;


import static android.R.attr.id;

public class Station
{
    private String countryTitle;
    private int id;
    private String cityTitle;
    private String regionTitle;
    private String name;

    public Station(String countryTitle, int id, String cityTitle, String regionTitle, String name) {
        this.countryTitle = countryTitle;
        this.id = id;
        this.cityTitle = cityTitle;
        this.regionTitle = regionTitle;
        this.name = name;
    }

    public String getCountryTitle() {
        return countryTitle;
    }

    public int getId() {
        return id;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }

    public String getName() {
        return name;
    }
}
