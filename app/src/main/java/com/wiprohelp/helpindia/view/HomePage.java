package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wiprohelp.helpindia.R;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.need_help_id).setOnClickListener(this);
        findViewById(R.id.to_help_id).setOnClickListener(this);
        findViewById(R.id.track_request_id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.need_help_id:
                Intent intent1 = new Intent(this, RequestCategoryView.class);
                startActivity(intent1);
                break;
            case R.id.to_help_id:
                Intent intent2 = new Intent(this, RegistrationView.class);
                startActivity(intent2);
                break;
            case R.id.track_request_id:
                Intent intent3 = new Intent(this, TrackMyRequestContactView.class);
                startActivity(intent3);
                break;
        }
    }

}
