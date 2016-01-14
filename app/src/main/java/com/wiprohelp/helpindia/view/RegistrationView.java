package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.Requests.LoadVolunteerProfileOperation;
import com.wiprohelp.helpindia.Requests.TrackRequestOperation;
import com.wiprohelp.helpindia.model.TrackRequestArray;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.HelpIndiaSharedPref;
import com.wiprohelp.helpindia.utilities.NetworkCheckBaseActivity;
import com.wiprohelp.helpindia.utilities.SelectOptionAlert;

import java.util.ArrayList;

public class RegistrationView extends NetworkCheckBaseActivity implements View.OnClickListener, SelectOptionAlert.MultiSelectListener {

    private static final String TAG = "RegistrationView";
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private Button registrationLocationButton;
    private Button registrationProfessionButton;
    private Button registrationDayButton;
    private Button registrationTimmingButton;
    private Button registrationProceedButton;
    private Button alreadyRegisteredActionButton;
    private Button alreadyRegisteredEditProfile;
    private Button alreadyRegisteredNewVolunteer;
    private TextView alreadyRegisteredLayoutTextView;
    private RelativeLayout alreadyRegisteredLayout;
    private String selectedAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registrationLocationButton = (Button) findViewById(R.id.registration_location_button);
        registrationProfessionButton = (Button) findViewById(R.id.registration_profession_button);
        registrationDayButton = (Button) findViewById(R.id.registration_day_button);
        registrationTimmingButton = (Button) findViewById(R.id.registration_timming_button);
        registrationProceedButton = (Button) findViewById(R.id.registration_action_button);
        alreadyRegisteredEditProfile = (Button) findViewById(R.id.already_registered_edit_profile);
        alreadyRegisteredActionButton = (Button) findViewById(R.id.already_registered_action_button);
        alreadyRegisteredNewVolunteer = (Button) findViewById(R.id.already_registered_new_volunteer);
        alreadyRegisteredLayoutTextView = (TextView) findViewById(R.id.already_registered_layout_textView);
        registrationLocationButton.setOnClickListener(this);
        registrationProfessionButton.setOnClickListener(this);
        registrationDayButton.setOnClickListener(this);
        registrationTimmingButton.setOnClickListener(this);
        registrationProceedButton.setOnClickListener(this);
        alreadyRegisteredEditProfile.setOnClickListener(this);
        alreadyRegisteredActionButton.setOnClickListener(this);
        alreadyRegisteredNewVolunteer.setOnClickListener(this);

        HelpIndiaSharedPref helpIndiaSharedPref = new HelpIndiaSharedPref(this);
        String volunteerMobile = helpIndiaSharedPref.getVolunteerMobile();
        alreadyRegisteredLayout = (RelativeLayout) findViewById(R.id.already_registered_layout);
        if(volunteerMobile.length() == 0){
            alreadyRegisteredLayout.setVisibility(View.INVISIBLE);
        }
        else{
            alreadyRegisteredLayout.setVisibility(View.VISIBLE);
            alreadyRegisteredLayoutTextView.setText(getResources().getString(R.string.already_registered_title_string)+" "+volunteerMobile);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registration_location_button:
                try {
                    Intent autocompleteIntent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
                    startActivityForResult(autocompleteIntent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.e(TAG, e.getMessage());}
                catch (GooglePlayServicesNotAvailableException e) {Log.e(TAG, e.getMessage());}
                break;
            case R.id.registration_profession_button:
                ArrayList<SelectOptionAlert.SelectOptionData> list1 = new ArrayList<SelectOptionAlert.SelectOptionData>();
                list1.add(new SelectOptionAlert.SelectOptionData("Doctor"));
                list1.add(new SelectOptionAlert.SelectOptionData("Physician"));
                list1.add(new SelectOptionAlert.SelectOptionData("Orthopedic"));
                list1.add(new SelectOptionAlert.SelectOptionData("Gynecologist"));
                list1.add(new SelectOptionAlert.SelectOptionData("ENT"));
                list1.add(new SelectOptionAlert.SelectOptionData("Cardiologist"));
                SelectOptionAlert selectOptionAlert1 = new SelectOptionAlert(list1, this);
                selectOptionAlert1.setMultiSelectListener(this, v);
                AlertDialog dialog1 = selectOptionAlert1.createMultipleChoiceOption("Select multiple option");
                dialog1.show();
                break;
            case R.id.registration_day_button:
                ArrayList<SelectOptionAlert.SelectOptionData> list2 = new ArrayList<SelectOptionAlert.SelectOptionData>();
                list2.add(new SelectOptionAlert.SelectOptionData("Monday"));
                list2.add(new SelectOptionAlert.SelectOptionData("Tuesday"));
                list2.add(new SelectOptionAlert.SelectOptionData("Wednesday"));
                list2.add(new SelectOptionAlert.SelectOptionData("Thursday"));
                list2.add(new SelectOptionAlert.SelectOptionData("Friday"));
                list2.add(new SelectOptionAlert.SelectOptionData("Saturday"));
                SelectOptionAlert selectOptionAlert2 = new SelectOptionAlert(list2, this);
                selectOptionAlert2.setMultiSelectListener(this, v);
                AlertDialog dialog2 = selectOptionAlert2.createMultipleChoiceOption("Select multiple option");
                dialog2.show();
                break;
            case R.id.registration_timming_button:
                ArrayList<SelectOptionAlert.SelectOptionData> list3 = new ArrayList<SelectOptionAlert.SelectOptionData>();
                list3.add(new SelectOptionAlert.SelectOptionData("All the day"));
                list3.add(new SelectOptionAlert.SelectOptionData("Only in day time"));
                list3.add(new SelectOptionAlert.SelectOptionData("Only in night time"));
                list3.add(new SelectOptionAlert.SelectOptionData("06:00 to 09:00 AM"));
                list3.add(new SelectOptionAlert.SelectOptionData("12:00 to 03:00 PM"));
                list3.add(new SelectOptionAlert.SelectOptionData("05:00 to 09:00 PM"));
                SelectOptionAlert selectOptionAlert3 = new SelectOptionAlert(list3, this);
                selectOptionAlert3.setMultiSelectListener(this, v);
                AlertDialog dialog3 = selectOptionAlert3.createMultipleChoiceOption("Select multiple option");
                dialog3.show();
                break;
            case R.id.registration_action_button:
                Intent intent = new Intent(this, ContactDetailView.class);
                intent.putExtra(Constants.VOLUNTEER_REGISTRATION_ADDRESS, selectedAddress);
                intent.putExtra(Constants.VOLUNTEER_REGISTRATION_DAYS_AVAILABLE, registrationDayButton.getText());
                intent.putExtra(Constants.VOLUNTEER_REGISTRATION_TIME_AVAILABLE, registrationTimmingButton.getText());
                intent.putExtra(Constants.VOLUNTEER_REGISTRATION_SPECIALITY, registrationProfessionButton.getText());
                // Empty because map is not there in this screen.
                intent.putExtra(Constants.REGISTRATION_LATITUDE, "0.0");
                intent.putExtra(Constants.REGISTRATION_LONGITUDE, "0.0");
                intent.putExtra(Constants.CONTACT_VIEW_MODE, Constants.CONTACT_VIEW_VOLUNTEER);
                startActivity(intent);
                break;
            case R.id.already_registered_edit_profile:
                //load Data
                break;
            case R.id.already_registered_action_button:
                Intent intent1 = new Intent(this, VolunteerActionView.class);
                startActivity(intent1);
                break;
            case R.id.already_registered_new_volunteer:
                alreadyRegisteredLayout.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                registrationLocationButton.setText(place.getName());
                selectedAddress = place.getAddress().toString();
            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e(TAG, status.getStatusMessage());
            }
            else if (resultCode == RESULT_CANCELED) {
            }
        }
    }

    @Override
    public void onMultiSelectDone(String selectedItems, View view) {
        if(selectedItems.length() != 0) {
            switch (view.getId()) {
                case R.id.registration_profession_button:
                    registrationProfessionButton.setText(selectedItems);
                    break;
                case R.id.registration_day_button:
                    registrationDayButton.setText(selectedItems);
                    break;
                case R.id.registration_timming_button:
                    registrationTimmingButton.setText(selectedItems);
                    break;
            }
        }
    }

//    @Override
//    protected void sendRequestToServer(){
//        super.sendRequestToServer();
//        HelpIndiaSharedPref helpIndiaSharedPref = new HelpIndiaSharedPref(this);
//        String mobile = helpIndiaSharedPref.getVolunteerMobile();
//        if(mobile != null && mobile.length() > 0) {
//            LoadVolunteerProfileOperation loadVolunteerProfileOperation = new LoadVolunteerProfileOperation(mobile);
//            VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(loadVolunteerProfileOperation);
//        }
//    }
//
//    @Subscribe
//    public void serverResponse(TrackRequestArray data){
//
//    }
}
