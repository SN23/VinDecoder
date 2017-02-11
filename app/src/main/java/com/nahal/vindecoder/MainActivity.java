package com.nahal.vindecoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView make = (TextView) findViewById(R.id.make);
        final TextView model = (TextView) findViewById(R.id.model);
        final TextView interiorColor = (TextView) findViewById(R.id.interiorColor);
        final TextView exteriorColor = (TextView) findViewById(R.id.exteriorColor);
        final TextView baseMSRP = (TextView) findViewById(R.id.baseMSRP);
        final TextView vehicleSize = (TextView) findViewById(R.id.vehicleSize);
        final TextView cityMPG = (TextView) findViewById(R.id.cityMPG);
        final TextView highwayMPG = (TextView) findViewById(R.id.highwayMPG);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        final EditText inputVIN = (EditText) findViewById(R.id.inputVIN);

//        Test VIN 2G1FC3D33C9165616

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VIN;
                VIN = inputVIN.getText().toString().trim();
                if (VIN.length() == 17) {
                    new FetchVehicleInfoTask(make, model, interiorColor, exteriorColor, baseMSRP,
                            vehicleSize, cityMPG, highwayMPG).execute(VIN);
                }

                else {
                    Toast.makeText(getApplicationContext(), "Invalid VIN", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}