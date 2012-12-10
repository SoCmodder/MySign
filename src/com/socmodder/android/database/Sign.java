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
    int id;
    @DatabaseField(indexName = "county")
    String county;
    @DatabaseField(indexName = "state")
    String state;
    @DatabaseField(indexName = "city")
    String city;

    @DatabaseField(indexName = "streetName")
    String streetName;
    @DatabaseField(indexName = "streetIntersection")
    String intersection;

    @DatabaseField(indexName = "lat")
    double longitude;
    @DatabaseField(indexName = "lon")
    double latitude;

    @DatabaseField
    double reflectivityReading;

    @DatabaseField
    Date installDate;
    @DatabaseField
    Date replaceDate;
    @DatabaseField
    Date maintenanceDate;

    @DatabaseField
    String signManufacturer;
    @DatabaseField
    String postType;

    Sign(){
        //needed by ormlite
    }

    public Sign(String county, String state, String city, String streetName, String intersection){
        this.county = county;
        this.state = state;
        this.city = city;
        this.streetName = streetName;
        this.intersection = intersection;
    }
}
