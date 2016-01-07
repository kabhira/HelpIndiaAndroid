package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.utilities.Constants;

public class ContactDetailView extends AppCompatActivity implements View.OnClickListener {

    private EditText phone1;
    private EditText phone2;
    private Button actionButton;
    private String screenMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        phone1 = (EditText) findViewById(R.id.contact_detail_editText1);
        phone2 = (EditText) findViewById(R.id.contact_detail_editText2);
        actionButton = (Button) findViewById(R.id.contact_detail_action_button);
        actionButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        screenMode = bundle.getString(Constants.CONTACT_VIEW_MODE);
        if(screenMode.equals(Constants.CONTACT_VIEW_SEEKER)){
            actionButton.setText(getResources().getString(R.string.contact_detail_action_seeker_string));
        }
        else if(screenMode.equals(Constants.CONTACT_VIEW_VOLUNTEER)){
            actionButton.setText(getResources().getString(R.string.contact_detail_action_volunteer_string));
        }
//        String categoryID = getIntent().getExtras().getString(Constants.REQUEST_CONTACTID);
//        intent.putExtra(Constants.REQUEST_LATITUDE, "");
//        intent.putExtra(Constants.REQUEST_LONGITUDE, "");
//        intent.putExtra(Constants.REQUEST_DURATION, requestDurationSpinner.getSelectedItem().toString());
//        intent.putExtra(Constants.REQUEST_PROBLEM, problemEditText.getText().toString());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact_detail_action_button:
                if(validate()){

                    if(screenMode.equals(Constants.CONTACT_VIEW_SEEKER)){
                        Intent intent = new Intent(this, HelpRequestResultView.class);
                        startActivity(intent);
                    }
                    else if(screenMode.equals(Constants.CONTACT_VIEW_VOLUNTEER)){
                        Intent intent = new Intent(this, VolunteerRegistrationConfirmationView.class);
                        startActivity(intent);
                    }
                    break;
                }
        }
    }

    private boolean validate(){
        boolean flag1 = true;
        boolean flag2 = false;
        String phone1Text = phone1.getText().toString();
        String phone2Text = phone2.getText().toString();
        if(phone1Text != null){
            if(phone1Text.length() == 0){
                phone1.setError(getResources().getString(R.string.contact_detail_phone_required_error_string));
                flag1 = false;
            }
            else if(phone1Text.length() < 10) {
                phone1.setError(getResources().getString(R.string.contact_detail_phone_error_string));
                flag1 = false;
            }
        }
        if(phone2Text != null){
            if(phone2Text.length() == 0){
                // This field is optional. Pass if it is empty.
                flag2 = true;
            }
            else if(phone2Text.length() < 10){
                phone2.setError(getResources().getString(R.string.contact_detail_phone_error_string));
                flag2 = false;
            }
        }
        return flag1 && flag2;
    }
}
