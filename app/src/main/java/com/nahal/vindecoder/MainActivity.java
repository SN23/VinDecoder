package com.nahal.vindecoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView make = (TextView) findViewById(R.id.make);
        TextView model = (TextView) findViewById(R.id.model);
        TextView interiorColor = (TextView) findViewById(R.id.interiorColor);
        TextView exteriorColor = (TextView) findViewById(R.id.exteriorColor);
        TextView baseMSRP = (TextView) findViewById(R.id.baseMSRP);
        TextView vehicleSize = (TextView) findViewById(R.id.vehicleSize);
        TextView cityMPG = (TextView) findViewById(R.id.cityMPG);
        TextView highwayMPG = (TextView) findViewById(R.id.highwayMPG);

        new FetchVehicleInfoTask(make, model, interiorColor, exteriorColor, baseMSRP,
                vehicleSize, cityMPG, highwayMPG).execute("2G1FC3D33C9165616");
    }
}