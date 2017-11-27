package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.CustomAlertDialog;
import com.wiprohelp.helpindia.utilities.HelpIndiaSharedPref;
import com.wiprohelp.helpindia.utilities.SelectOptionAlert;

import java.util.ArrayList;

public class RegistrationView extends AppCompatActivity implements View.OnClickListener, SelectOptionAlert.MultiSelectListener {

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
    private CustomAlertDialog customAlertDialog;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mDrawerTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = mDrawerTitle = getTitle();
        mDrawerTitles = getResources().getStringArray(R.array.register_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_register);
        mDrawerList = (ListView) findViewById(R.id.register_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.mipmap.ic_launcher,  /* nav drawer image to replace 'Up' caret */
                R.string.app_name,  /* "open drawer" description for accessibility */
                R.string.app_name  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


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
        customAlertDialog = new CustomAlertDialog(this);

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
    protected void onStop() {
        super.onStop();
        customAlertDialog.dismissProgressDialog();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Intent intent;
        switch(position){
            case 0:
                intent = new Intent(RegistrationView.this,YourRequests.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(RegistrationView.this,VolunteerActionView.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registration_location_button:
                try {
                    Intent autocompleteIntent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(this);
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
                customAlertDialog.createProgressDialog("Please Wait");
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
