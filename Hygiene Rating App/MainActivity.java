package com.example.week20;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
ArrayList<Business> businesses;
HygieneWebService HWS = new HygieneWebService(this);
    private MapView mapView;
    //private MapboxMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        //map = mapboxMap;
        mapboxMap.setStyle(Style.OUTDOORS);
//Displays a map of the location searched for
        mapboxMap.setCameraPosition(
                new CameraPosition.Builder()
                .target(new LatLng(53.472,-2.239))
                .zoom(8.0)
                .build()
        );
    }
    //@Override
    //public void onStyleLoaded(@NonNull final Style style) {


    //}

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void onClick(View v)
    {
        TableLayout displayTable = (TableLayout) findViewById(R.id.table);
        displayTable.removeAllViews();
        MapView mp = findViewById(R.id.mapView);
        mp.setVisibility(View.INVISIBLE);
        final RadioButton locationButton = (RadioButton) findViewById(R.id.locationButton);
        final RadioButton placeButton = (RadioButton) findViewById(R.id.placeButton);
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void[] v)
            {
                if(locationButton.isChecked())
                {//Searches by location of the user
                    String location = requestLocation();
                    businesses = HWS.makeRequest("","_location&" + location);
                }
                else if (placeButton.isChecked())
                {//Searches establishments by their name
                    EditText editText = (EditText) findViewById(R.id.editText);
                    String unformattedPC = editText.getText().toString();
                    businesses = HWS.makeRequest(unformattedPC,"_name&name=");
                }
                else
                {//Searches by postcode entered
                    EditText editText = (EditText) findViewById(R.id.editText);
                    String unformattedPC = editText.getText().toString();
                    businesses = HWS.makeRequest(unformattedPC,"_postcode&postcode=");
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void v) {
                for(int i = 0; i<businesses.size(); i++)
                {//Displays them in a table
                    setTableRow(businesses.get(i).getBusinessName(), Integer.toString(businesses.get(i).getRatingValue()), 0);
                    setTableRow(businesses.get(i).getAddressLine1() + ", " + businesses.get(i).getAddressLine2() + ", " + businesses.get(i).getAddressLine3(), businesses.get(i).getPostCode(), 50);
                    //longitude[i] = establishments.get(i).getLongitude();
                    //latitude[i] = establishments.get(i).getLatitude();
                }
            }
        }.execute();


    }
    public void mapClick(View v)
    {
        MapView mp = findViewById(R.id.mapView);
        mp.setVisibility(View.VISIBLE);
//Shows the map when the button is pressed
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setTableRow(String column1Content, String column2Content, int bottomMargin)
    {//Defines the layout of the table, rows, columns, etc
        TableLayout table = (TableLayout) findViewById(R.id.table);
        TableRow tableRow = new TableRow(this);
        tableRow.setId(View.generateViewId());
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView column1 = new TextView(this);
        column1.setId(View.generateViewId());
        column1.setText(column1Content);
        column1.setPadding(5,5,10,bottomMargin);
        column1.setWidth(800);
        tableRow.addView(column1);

        TextView column2 = new TextView(this);
        column2.setId(View.generateViewId());
        column2.setText(column2Content);
        column2.setPadding(5,5,5,bottomMargin);
        tableRow.addView(column2);

        table.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    public String requestLocation()
    {//Asks to get the users location
        String[] requiredPermissions = {Manifest.permission.INTERNET,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        boolean ok = true;

        for(int i = 0; i<requiredPermissions.length;i++)
        {
            int result = ActivityCompat.checkSelfPermission(this, requiredPermissions[i]);
            if(result != PackageManager.PERMISSION_GRANTED)
            {
                ok = false;
            }

        }
        if(!ok)
        {
            ActivityCompat.requestPermissions(this, requiredPermissions, 1);
            System.exit(0);
            return "lat="+"&long=";
        }
        else
        {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            System.out.println("Latitude: "+lat+" Longitude: "+lng);
            return "lat="+lat+"&long="+lng;
        }
    }
}
