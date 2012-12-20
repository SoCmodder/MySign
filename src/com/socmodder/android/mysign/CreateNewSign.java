package com.socmodder.android.mysign;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.*;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 12/9/12
 * Time: 3:42 PM
 */
public class CreateNewSign extends Activity {
    EditText state, city, county, street, intersection, reflectivity;
    DatePicker install, replace, maint;
    CheckBox installBox, replaceBox, maintBox;
    static ImageButton takePhoto;
    LocationManager locationManager;
    Location loc;
    LocationProvider provider;
    AlertDialog GPSDialog;
    Bitmap mImageBitmap;
    String imageName;
    CompoundButton.OnCheckedChangeListener checkboxListener;
    Button submitButton;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_sign_layout);

        setupLayoutItems();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 0 && resultCode == RESULT_OK){
            mImageBitmap = (Bitmap) data.getExtras().get("data");
            takePhoto.setImageBitmap(mImageBitmap);
        }
    }

    public void setupLayoutItems(){
        state = (EditText)findViewById(R.id.state_et);
        city = (EditText)findViewById(R.id.city_et);
        county = (EditText)findViewById(R.id.county_et);
        street = (EditText)findViewById(R.id.street_et);
        intersection = (EditText)findViewById(R.id.intersection_et);
        reflectivity = (EditText)findViewById(R.id.reflect_et);

        takePhoto = (ImageButton)findViewById(R.id.thumb_imageview);

        submitButton = (Button)findViewById(R.id.submit_button);

        checkboxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        };

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String State, City, County, Street, Intersection, Reflectivity;
                State = state.getText().toString();
                City = city.getText().toString();
                County = county.getText().toString();
                Street = street.getText().toString();
                Intersection = intersection.getText().toString();
                Reflectivity = reflectivity.getText().toString();
            }
        });
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

