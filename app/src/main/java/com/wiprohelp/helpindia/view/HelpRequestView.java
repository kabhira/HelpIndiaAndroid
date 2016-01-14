package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.model.DataManager;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.SelectOptionAlert;

import java.util.ArrayList;

public class HelpRequestView extends MapBaseActivity implements View.OnClickListener, SelectOptionAlert.SingleSelectListener {

    private TextView problemText;
    private TextView requestLocationTextview;
    private EditText problemEditText;
    private Button requestLocationButton;
    private Button requestAction;
    private Button requestDurationButton;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static String TAG = "HelpRequestView";
    private String selectedAddress;
    private String categoryID;


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
        requestDurationButton = (Button) findViewById(R.id.request_duration_button);
        requestAction.setOnClickListener(this);
        requestLocationButton.setOnClickListener(this);
        requestDurationButton.setOnClickListener(this);
        setMapView(R.id.request_map_view, savedInstanceState);

        categoryID = getIntent().getExtras().getString(Constants.REQUEST_CONTACTID);
        if (!categoryID.equals("100")) {
            problemText.setText(getResources().getString(R.string.new_request_problem_title));
            problemEditText.setText(DataManager.instance().getRequestCategoryMap().get(categoryID).getCategoryName());
            problemEditText.setFocusable(false);
        }
    }

    @Override
    public void onSingleSelect(String selectedItem) {
        requestDurationButton.setText(selectedItem);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.request_action_button:
                Intent intent = new Intent(this, ContactDetailView.class);
                intent.putExtra(Constants.CONTACT_VIEW_MODE, Constants.CONTACT_VIEW_VICTIM);
                intent.putExtra(Constants.REQUEST_CONTACTID, categoryID);
                intent.putExtra(Constants.REQUEST_PROBLEM, problemEditText.getText().toString());
                intent.putExtra(Constants.REGISTRATION_LATITUDE, selectedLatitude+"");
                intent.putExtra(Constants.REGISTRATION_LONGITUDE, selectedLongitude+"");
                intent.putExtra(Constants.REQUEST_ADDRESS, selectedAddress);
                intent.putExtra(Constants.REQUEST_DURATION, requestDurationButton.getText().toString());
                startActivity(intent);
                break;
            case R.id.request_location_button: {
                try {
                    Intent autocompleteIntent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
                    startActivityForResult(autocompleteIntent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.e(TAG, e.getMessage());
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.e(TAG, e.getMessage());
                }
                break;
            }
            case R.id.request_duration_button:{
                ArrayList<SelectOptionAlert.SelectOptionData> durationList = new ArrayList<SelectOptionAlert.SelectOptionData>();
                durationList.add(new SelectOptionAlert.SelectOptionData("ASAP"));
                durationList.add(new SelectOptionAlert.SelectOptionData("Today"));
                durationList.add(new SelectOptionAlert.SelectOptionData("Tomorrow"));
                durationList.add(new SelectOptionAlert.SelectOptionData("In 2 days"));
                durationList.add(new SelectOptionAlert.SelectOptionData("In 4 days"));
                SelectOptionAlert selectOptionAlert = new SelectOptionAlert(durationList, this);
                selectOptionAlert.setSingleSelectListener(this);
                selectOptionAlert.ceateSingleChoiceOption().show();
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                requestLocationButton.setText(place.getName());
                selectedAddress = place.getAddress().toString();
                updateMapUI(place.getLatLng());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }

}
