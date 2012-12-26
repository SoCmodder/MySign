package com.socmodder.android.mysign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button createNewSign, searchSignButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Intent createSign = new Intent(this, CreateNewSign.class);
        final Intent searchSigns = new Intent(this, SearchSigns.class);
        createSign.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        searchSigns.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        createNewSign = (Button)findViewById(R.id.newSignButton);
        searchSignButton = (Button)findViewById(R.id.searchSignsButton);

        createNewSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(createSign);
            }
        });

        searchSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(searchSigns);
            }
        });
    }
}
