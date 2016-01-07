package com.wiprohelp.helpindia.view;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.model.DataManager;
import com.wiprohelp.helpindia.utilities.Constants;

public class HelpRequestView extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, ResultCallback<LocationSettingsResult> {

    private TextView problemText;
    private TextView requestLocationTextview;
    private EditText problemEditText;
    private Button requestLocationButton;
    private Button requestAction;
    private Spinner requestDurationSpinner;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private MapView mapView;
    private GoogleMap mMap;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final int GPS_RESOLUTION_REQUEST_CODE = 100;
    private static String TAG = "HelpRequestView";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_request_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        requestLocationTextview = (TextView) findViewById(R.id.request_location_textview);
        problemText = (TextView) findViewById(R.id.request_problem_textview);
        problemEditText = (EditText) findViewById(R.id.request_problem_edittext);
        requestAction = (Button) findViewById(R.id.request_action_button);
        requestLocationButton = (Button) findViewById(R.id.request_location_button);
        requestDurationSpinner = (Spinner) findViewById(R.id.request_duration_spinner);
        requestAction.setOnClickListener(this);
        requestLocationButton.setOnClickListener(this);
        mapView = (MapView) findViewById(R.id.request_map_view);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);

        String categoryID = getIntent().getExtras().getString(Constants.REQUEST_CONTACTID);
        if(!categoryID.equals("100")){
            problemText.setText(getResources().getString(R.string.new_request_problem_title));
            problemEditText.setText(DataManager.instance().getRequestCategoryList().get(categoryID).getCatName());
            problemEditText.setFocusable(false);
        }
        buildGoogleApiClient();
        createLocationRequest();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClientConnect();
    }

    @Override
    public void onResume() {
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
        googleApiClientDisconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.request_action_button:
                Intent intent = new Intent(this, ContactDetailView.class);
                intent.putExtra(Constants.CONTACT_VIEW_MODE, Constants.CONTACT_VIEW_SEEKER);
                intent.putExtra(Constants.REQUEST_LATITUDE, "");
                intent.putExtra(Constants.REQUEST_LONGITUDE, "");
                intent.putExtra(Constants.REQUEST_DURATION, requestDurationSpinner.getSelectedItem().toString());
                intent.putExtra(Constants.REQUEST_PROBLEM, problemEditText.getText().toString());
                startActivity(intent);
                break;
            case R.id.request_location_button: {
                try {
                    Intent autocompleteIntent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
                    startActivityForResult(autocompleteIntent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {Log.e(TAG, e.getMessage());}
                catch (GooglePlayServicesNotAvailableException e) {Log.e(TAG, e.getMessage());}
                break;
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        updateMapToCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void googleApiClientConnect()
    {
        if (!mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.connect();
        }
    }

    private void googleApiClientDisconnect()
    {
        if (mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onResult(LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult(this, GPS_RESOLUTION_REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, e.getMessage());
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                break;
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void updateUI(LatLng latLng) {
        if(mMap != null){
            mMap.clear();
            //mMap.setMyLocationEnabled(true);
            mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        }
    }

    private Location getCurrentLocation(){
        Location mCurrentLocation = null;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        return mCurrentLocation;
    }

    private void updateMapToCurrentLocation(){
        Location location = getCurrentLocation();
        if(location != null){
            updateUI(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                requestLocationButton.setText(place.getName());
                updateUI(place.getLatLng());
            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e(TAG, status.getStatusMessage());
            }
            else if (resultCode == RESULT_CANCELED) {
            }
        }
        else if(requestCode == GPS_RESOLUTION_REQUEST_CODE){
            updateMapToCurrentLocation();
        }
    }

}
