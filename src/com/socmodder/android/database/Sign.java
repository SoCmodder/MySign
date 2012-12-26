package com.socmodder.android.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 12/9/12
 * Time: 2:54 PM
 */
@DatabaseTable
public class Sign {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(indexName = "county")
    private String county;
    @DatabaseField(indexName = "state")
    private String state;
    @DatabaseField(indexName = "city")
    private String city;

    @DatabaseField(indexName = "streetName")
    private String streetName;
    @DatabaseField(indexName = "streetIntersection")
    private String intersection;

    @DatabaseField(indexName = "lat")
    private double longitude;
    @DatabaseField(indexName = "lon")
    private double latitude;

    @DatabaseField
    private double reflectivityReading;

    @DatabaseField
    private Date installDate;
    @DatabaseField
    private Date replaceDate;
    @DatabaseField
    private Date maintenanceDate;

    @DatabaseField
    private String signManufacturer;
    @DatabaseField
    private String postType;

    Sign(){
        //needed by ormlite
    }

    public Sign(String county, String state, String city, String streetName, String intersection, double lat, double lon, double reflect){
        this.county = county;
        this.state = state;
        this.city = city;
        this.streetName = streetName;
        this.intersection = intersection;

        this.longitude = lon;
        this.latitude = lat;
        this.reflectivityReading = reflect;
    }

    public String getStreetName(){
        return this.streetName;
    }

    public String getCity(){
        return this.city;
    }
}
