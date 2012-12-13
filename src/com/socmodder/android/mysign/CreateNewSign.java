package com.socmodder.android.mysign;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 12/9/12
 * Time: 3:42 PM
 */
public class CreateNewSign extends Activity {
    EditText state, city, county, street, intersection, reflectivity;
    DatePicker install, replace, maint;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_sign_layout);

        state = (EditText)findViewById(R.id.state_tv);
        city = (EditText)findViewById(R.id.city_tv);
        county = (EditText)findViewById(R.id.county_tv);
        street = (EditText)findViewById(R.id.streetname_tv);
        intersection = (EditText)findViewById(R.id.intersection_tv);
        reflectivity = (EditText)findViewById(R.id.reflectivity_tv);

        install = (DatePicker)findViewById(R.id.install_datepicker);
        replace = (DatePicker)findViewById(R.id.replace_datepicker);
        maint = (DatePicker)findViewById(R.id.maint_datepicker);
    }
}