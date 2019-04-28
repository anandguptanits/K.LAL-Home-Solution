package com.klal.www.techteamk;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.nfc.Tag;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GetLocation extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final int SUCCESS_RESULT = 0;
    private static final int FAILURE_RESULT = 1;
    private static final String PACKAGE_NAME = "com.klal.www.techteamk";
    private static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    private static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    private static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";

    private static final String TAG = "Connection failed";
    private TextView latitude;
    private TextView longitude;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Double mylongi;
    private Double mylati;
    private static int MY_PERMISSION_REQUEST_FINE_LOCATION=101;
    private Button getAddress;
    private TextView address;
    private FusedLocationProviderClient mFusedLocationClient;
    protected Location mLastKnownLocation;
    private AddressResultReceiver mResultReceiver;
    private String mAddressOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        latitude = findViewById(R.id.Latitude);
        longitude = findViewById(R.id.Longitude);
        getAddress = (Button) findViewById(R.id.GetAddress);
        address = (TextView) findViewById(R.id.Address);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(1000);
        getAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAddressButtonHandler(view);
            }
        });
    }

    private void fetchAddressButtonHandler(View view) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {

                @Override
                public void onSuccess(Location location) {
                    mLastKnownLocation = location;

                    // in some rare cases the location returns null
                    if (mLastKnownLocation == null) {
                        return;
                    }

                    if (!Geocoder.isPresent()) {
                        Toast.makeText(GetLocation.this, "No Geocoder present", Toast.LENGTH_LONG).show();
                        return;
                    }

                    //Start service and update UI
                    startIntentService();
                }
            });
        }
    }

    private void startIntentService() {

        Intent intent = new Intent(this, FetchAddressIntentService.class);
        mResultReceiver = new AddressResultReceiver (new Handler());
        intent.putExtra (RECEIVER, mResultReceiver);
        intent.putExtra(LOCATION_DATA_EXTRA, mLastKnownLocation);

        startService(intent);

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocationUpdates();
    }

    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSION_REQUEST_FINE_LOCATION);
            }
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection Failed" + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        mylati=location.getLatitude();
        mylongi=location.getLongitude();
        latitude.setText("Lattitude:" +String.valueOf(mylati));
        longitude.setText("Longitude:" +String.valueOf(mylongi));
    }

    protected void onStart() {
        super.onStart();

        googleApiClient.connect();

    }

    protected void onResume() {
        super.onResume();
        if(googleApiClient.isConnected()){
            requestLocationUpdates();
        }

    }

    protected void onPause() {
        super.onPause();
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
    }

    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode==MY_PERMISSION_REQUEST_FINE_LOCATION){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }  else {
                Toast.makeText(getApplicationContext(),"The app requires permissinon to be granted",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            //Display the address string
            //or error message sent from Intent service
            mAddressOutput = resultData.getString(RESULT_DATA_KEY);
            displayAddressOutput();

            if (resultCode == SUCCESS_RESULT) {
                Toast.makeText(getApplicationContext(), "Address Found", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void displayAddressOutput() {
        address.setText(mAddressOutput);
    }
}

