package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;

import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.Requests.RequestCategoryOperation;
import com.wiprohelp.helpindia.gcm.GcmHandler;
import com.wiprohelp.helpindia.model.DataManager;
import com.wiprohelp.helpindia.model.RequestCategoryArray;
import com.wiprohelp.helpindia.utilities.CustomApplication;
import com.wiprohelp.helpindia.utilities.NetworkCheckBaseActivity;

public class HelpIndiaWelcome extends NetworkCheckBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_india_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataManager.instance();
        GcmHandler gcmHandler = new GcmHandler();
        gcmHandler.gcmRegistration(this);
    }

    @Override
    protected void sendRequestToServer(){
        super.sendRequestToServer();
        RequestCategoryOperation requestCategoryOperation = new RequestCategoryOperation();
        VolleyNetwork.getInstance(CustomApplication.getmContext()).addToRequestQueue(requestCategoryOperation);
    }

    @Subscribe
    public void serverResponse(RequestCategoryArray data){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HelpIndiaWelcome.this, HomePage.class);
                HelpIndiaWelcome.this.startActivity(intent);
                HelpIndiaWelcome.this.finish();
            }
        }, 2000);
    }

}
