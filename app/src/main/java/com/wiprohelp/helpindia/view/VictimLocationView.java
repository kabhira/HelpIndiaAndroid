package com.wiprohelp.helpindia.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.utilities.Constants;

public class VictimLocationView extends MapBaseActivity implements View.OnClickListener {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim_location_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setMapView(R.id.victim_location_mapview, savedInstanceState);
        TextView victimLocationName = (TextView) findViewById(R.id.victim_location_name);
        TextView victimLocationAddress = (TextView) findViewById(R.id.victim_location_address);
        TextView victimLocationMobile = (TextView) findViewById(R.id.victim_location_mobile);
        victimLocationMobile.setOnClickListener(this);

        bundle = getIntent().getExtras();
        victimLocationName.setText(bundle.getString(Constants.VICTIM_NAME));
        victimLocationAddress.setText(bundle.getString(Constants.VICTIM_ADDRESS));
        victimLocationMobile.setText(bundle.getString(Constants.VICTIM_MOBILE));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.victim_location_mobile:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + bundle.getString(Constants.VICTIM_MOBILE)));
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void updateMapUI(LatLng latLng) {
        LatLng latLng1 = new LatLng(bundle.getDouble(Constants.VICTIM_LATITUDE), bundle.getDouble(Constants.VICTIM_LONGITUDE));
        super.updateMapUI(latLng1);
    }
}
