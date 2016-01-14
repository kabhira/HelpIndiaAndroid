package com.wiprohelp.helpindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.Requests.HelpRequestUpdateOperation;
import com.wiprohelp.helpindia.Requests.LiveRequestOperation;
import com.wiprohelp.helpindia.model.LiveRequestArray;
import com.wiprohelp.helpindia.model.LiveRequestElement;
import com.wiprohelp.helpindia.model.ServerResponseElement;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.HelpIndiaSharedPref;
import com.wiprohelp.helpindia.utilities.NetworkCheckBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class VolunteerActionView extends NetworkCheckBaseActivity {

    private ArrayList<LiveRequestElement> mDataset;
    private VolunteerActionAdapter mAdapter;
    private static String TAG = "VolunteerActionView";
    private LiveRequestElement mLiveRequestElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_action_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataset = new ArrayList<LiveRequestElement>();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.volunteer_action_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new VolunteerActionAdapter(mDataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void sendRequestToServer(){
        super.sendRequestToServer();
        HelpIndiaSharedPref helpIndiaSharedPref = new HelpIndiaSharedPref(this);
        String mobile = helpIndiaSharedPref.getVolunteerMobile();
        // Right now for MVP, server is giving all the available request.
        LiveRequestOperation liveRequestOperation = new LiveRequestOperation(mobile);
        VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(liveRequestOperation);
    }

    public void sendStatusRequestUpdateToServer(LiveRequestElement liveRequestElement){
        mLiveRequestElement = liveRequestElement;
        HelpIndiaSharedPref helpIndiaSharedPref = new HelpIndiaSharedPref(this);
        JSONObject requestObj = new JSONObject();
        String query = "";
        try {
            requestObj.put("requestId", liveRequestElement.getRequestId());
            requestObj.put("requestStatus", "AWAITED");
            requestObj.put("mobileNumber", helpIndiaSharedPref.getVolunteerMobile());
            query = URLEncoder.encode(requestObj.toString(), "utf-8");
        }catch (JSONException | UnsupportedEncodingException e){
            Log.e(TAG, e.getMessage());}
        HelpRequestUpdateOperation helpRequestUpdateOperation = new HelpRequestUpdateOperation(query);
        VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(helpRequestUpdateOperation);
    }

    @Subscribe
    public void liveRequestResponse(LiveRequestArray data){
        mDataset.clear();
        mDataset.addAll(data.getData());
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void serverResponse(ServerResponseElement data){
        //switch to navigation screen
        Intent intent = new Intent(this, VictimLocationView.class);
        intent.putExtra(Constants.VICTIM_LATITUDE, Double.parseDouble(mLiveRequestElement.getVictimLatitude()));
        intent.putExtra(Constants.VICTIM_LONGITUDE, Double.parseDouble(mLiveRequestElement.getVictimLongitude()));
        intent.putExtra(Constants.VICTIM_MOBILE, mLiveRequestElement.getVictimPhoneNumber());
        intent.putExtra(Constants.VICTIM_NAME, mLiveRequestElement.getVictimName());
        intent.putExtra(Constants.VICTIM_ADDRESS, mLiveRequestElement.getVictimAddress());
        startActivity(intent);
        finish();
    }

}
