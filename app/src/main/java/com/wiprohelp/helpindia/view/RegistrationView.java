package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.model.VolunteerProfessionModel;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.SelectOptionAlert;

import java.util.ArrayList;

public class RegistrationView extends AppCompatActivity implements View.OnClickListener, SelectOptionAlert.MultiSelectListener {

    private static final String TAG = "RegistrationView";
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private Button registrationLocationButton;
    private Button registrationProfessionButton;
    private Button registrationDayButton;
    private Button registrationTimmingButton;
    private Button registrationActionButton;

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
        registrationActionButton = (Button) findViewById(R.id.registration_action_button);
        registrationLocationButton.setOnClickListener(this);
        registrationProfessionButton.setOnClickListener(this);
        registrationDayButton.setOnClickListener(this);
        registrationTimmingButton.setOnClickListener(this);
        registrationActionButton.setOnClickListener(this);
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
                intent.putExtra(Constants.CONTACT_VIEW_MODE, Constants.CONTACT_VIEW_VOLUNTEER);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                registrationLocationButton.setText(place.getName());
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
        switch (view.getId()){
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
