package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.Requests.HelpRequestOperation;
import com.wiprohelp.helpindia.Requests.VolunteerRegistrationOperation;
import com.wiprohelp.helpindia.events.EventBusSingleton;
import com.wiprohelp.helpindia.events.ResponseStatusFailedEvent;
import com.wiprohelp.helpindia.model.ServerResponseElement;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.HelpIndiaSharedPref;
import com.wiprohelp.helpindia.utilities.NetworkCheckBaseActivity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ContactDetailView extends NetworkCheckBaseActivity implements View.OnClickListener {

    private EditText contactName;
    private EditText contactAge;
    private EditText phone;
    private EditText phoneOptional;
    private Button actionButton;
    private String screenMode;
    private String mLatitude = "0.0";
    private String mLongitude = "0.0";
    private String mAddress;
    private static String TAG = "ContactDetailView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactName = (EditText) findViewById(R.id.contact_detail_name);
        contactAge = (EditText) findViewById(R.id.contact_detail_age);
        phone = (EditText) findViewById(R.id.contact_detail_editText1);
        phoneOptional = (EditText) findViewById(R.id.contact_detail_editText2);
        actionButton = (Button) findViewById(R.id.contact_detail_action_button);
        actionButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        screenMode = bundle.getString(Constants.CONTACT_VIEW_MODE);
        if(screenMode.equals(Constants.CONTACT_VIEW_VICTIM)){
            actionButton.setText(getResources().getString(R.string.contact_detail_action_seeker_string));
        }
        else if(screenMode.equals(Constants.CONTACT_VIEW_VOLUNTEER)){
            actionButton.setText(getResources().getString(R.string.contact_detail_action_volunteer_string));
        }

        // we are not getting location for volunteer. So by default its LatLng is 0.0/0.0
        try {
            Bundle intentBundle = getIntent().getExtras();
            mLatitude =  intentBundle.getString(Constants.REGISTRATION_LATITUDE);
            mLongitude = intentBundle.getString(Constants.REGISTRATION_LONGITUDE);
            mAddress = getAddress(Double.parseDouble(mLatitude), Double.parseDouble(mLongitude));
        }
        catch (Exception e){
            mLatitude = "0.0";
            mLongitude = "0.0";
            mAddress = "";
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact_detail_action_button:
                if(validate()){
                    raiseHelpRequest();
                    break;
                }
        }
    }

    private void raiseHelpRequest(){
        Bundle bundle = getIntent().getExtras();
        JSONObject requestObj = new JSONObject();
        String query = "";
        try {
            if(screenMode.equals(Constants.CONTACT_VIEW_VICTIM)){
                requestObj.put("optionalMobileNumber", phoneOptional.getText().toString());
                requestObj.put("userName", contactName.getText().toString());
                requestObj.put("userAge", Integer.parseInt(contactAge.getText().toString()) );
                requestObj.put("userAddress", mAddress);
                requestObj.put("helpExpectedTime", bundle.getString(Constants.REQUEST_DURATION));
                requestObj.put("mobileNumber", phone.getText().toString());
                requestObj.put("categoryId", Integer.parseInt(bundle.getString(Constants.REQUEST_CONTACTID)) );
                requestObj.put("categoryDesc", bundle.getString(Constants.REQUEST_PROBLEM));
                requestObj.put("latitude", mLatitude);
                requestObj.put("longitude", mLongitude);
                requestObj.put("gcmRegistrationToken", "");
                query = URLEncoder.encode(requestObj.toString(), "utf-8");
                HelpRequestOperation helpRequestOperation = new HelpRequestOperation(query);
                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(helpRequestOperation);
            }
            else if(screenMode.equals(Constants.CONTACT_VIEW_VOLUNTEER)){
                requestObj.put("optionalMobileNumber", phoneOptional.getText().toString());
                requestObj.put("userName", contactName.getText().toString());
                requestObj.put("userAge", Integer.parseInt(contactAge.getText().toString()) );
                requestObj.put("userAddress", mAddress);
                requestObj.put("daysAvailable", bundle.getString(Constants.VOLUNTEER_REGISTRATION_DAYS_AVAILABLE));
                requestObj.put("timeAvailable", bundle.getString(Constants.VOLUNTEER_REGISTRATION_TIME_AVAILABLE));
                requestObj.put("speciality", bundle.getString(Constants.VOLUNTEER_REGISTRATION_SPECIALITY));
                requestObj.put("mobileNumber", phone.getText().toString());
                requestObj.put("seakerProviderFlag", "1");
                requestObj.put("latitude", mLatitude);
                requestObj.put("longitude", mLongitude);
                requestObj.put("gcmRegistrationToken", "");
                query = URLEncoder.encode(requestObj.toString(), "utf-8");
                VolunteerRegistrationOperation registrationOperation = new VolunteerRegistrationOperation(query);
                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(registrationOperation);
            }
        }catch (JSONException | UnsupportedEncodingException e){Log.e(TAG, e.getMessage());}
    }

    @Subscribe
    public void serverResponse(ServerResponseElement data){
        HelpIndiaSharedPref helpIndiaSharedPref = new HelpIndiaSharedPref(this);
        if (screenMode.equals(Constants.CONTACT_VIEW_VICTIM)) {
            helpIndiaSharedPref.setVictimMobile(phone.getText().toString());
            Intent intent = new Intent(this, HelpRequestResultView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (screenMode.equals(Constants.CONTACT_VIEW_VOLUNTEER)) {
            helpIndiaSharedPref.setVolunteerMobile(phone.getText().toString());
            Intent intent = new Intent(this, VolunteerRegistrationConfirmationView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private boolean validate(){
        boolean returnFlag = true;
        String contactNameText = contactName.getText().toString();
        String contactAgeText = contactAge.getText().toString();
        String phoneText = phone.getText().toString();
        String phoneOptionalText = phoneOptional.getText().toString();
        if(contactNameText != null && contactNameText.length() == 0) {
            contactName.setError(getResources().getString(R.string.contact_detail_phone_required_error_string));
            returnFlag = false;
        }
        else if(contactAgeText != null && contactAgeText.length() == 0) {
            contactAge.setError(getResources().getString(R.string.contact_detail_phone_required_error_string));
            returnFlag = false;
        }
        else if(phoneText != null){
            if(phoneText.length() == 0){
                phone.setError(getResources().getString(R.string.contact_detail_phone_required_error_string));
                returnFlag = false;
            }
            else if(phoneText.length() < 10) {
                phone.setError(getResources().getString(R.string.contact_detail_phone_error_string));
                returnFlag = false;
            }
        }
        else if(phoneOptionalText != null){
            if(phoneOptionalText.length() == 0){
                // This field is optional. Pass if it is empty.
                returnFlag = true;
            }
            else if(phoneOptionalText.length() < 10){
                phoneOptional.setError(getResources().getString(R.string.contact_detail_phone_error_string));
                returnFlag = false;
            }
        }
        return returnFlag;
    }

    private String getAddress(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses = new ArrayList<Address>();
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        String addr = "";
        for(int i=0; i<4; i++){
            if(addresses.get(0).getAddressLine(i) != null)
                addr += addresses.get(0).getAddressLine(i)+", ";
        }
        int lastIndex = addr.lastIndexOf(", ");
        addr = new StringBuilder(addr).replace(lastIndex, lastIndex+1, "").toString();
        return addr;
    }
}
