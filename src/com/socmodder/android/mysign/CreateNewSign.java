package com.socmodder.android.mysign;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.*;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 12/9/12
 * Time: 3:42 PM
 */
public class CreateNewSign extends Activity {
    EditText state, city, county, street, intersection, reflectivity;
    DatePicker install, replace, maint;
    Button autofill;
    LocationManager locationManager;
    Location loc;
    LocationProvider provider;
    AlertDialog GPSDialog;

    private final LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            loc = location;
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onProviderEnabled(String s) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onProviderDisabled(String s) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_sign_layout);

        setupLayoutItems();

        autofill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setup a loading thing to wait while the device gets the location
                //then get the different aspects needed from the location data.
                if(loc != null){
                    Toast.makeText(getApplicationContext(), "Latitude: " + String.valueOf(loc.getLatitude()), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "We don't need no stinking GPS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void setupLayoutItems(){
        state = (EditText)findViewById(R.id.state_tv);
        city = (EditText)findViewById(R.id.city_tv);
        county = (EditText)findViewById(R.id.county_tv);
        street = (EditText)findViewById(R.id.streetname_tv);
        intersection = (EditText)findViewById(R.id.intersection_tv);
        reflectivity = (EditText)findViewById(R.id.reflectivity_tv);

        install = (DatePicker)findViewById(R.id.install_datepicker);
        replace = (DatePicker)findViewById(R.id.replace_datepicker);
        maint = (DatePicker)findViewById(R.id.maint_datepicker);

        autofill = (Button)findViewById(R.id.autofill_button);
    }

    @Override
    protected void onStart(){
        super.onStart();

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, listener);

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        createGPSSettingsDialog();

        if(!gpsEnabled){
            GPSDialog.show();
        }
    }

    public void createGPSSettingsDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.GPS_dialog_message);
        builder.setTitle(R.string.GPS_dialog_title);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                enableLocationSettings();
            }
        });
        builder.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Do something if they click no...
            }
        });

        GPSDialog = builder.create();
    }

    private void enableLocationSettings(){
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

}

