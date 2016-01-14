package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.HelpIndiaSharedPref;

public class TrackMyRequestContactView extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_my_request_contact_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.track_my_request_contact_editText);
        button = (Button) findViewById(R.id.track_my_request_contact_button);
        button.setOnClickListener(this);

        HelpIndiaSharedPref helpIndiaSharedPref = new HelpIndiaSharedPref(this);
        editText.setText(helpIndiaSharedPref.getVictimMobile());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.track_my_request_contact_button:
                Intent intent = new Intent(this, TrackMyRequestView.class);
                intent.putExtra(Constants.TRACK_REQUEST_MOBILE, editText.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
