package com.wiprohelp.helpindia.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.Requests.TrackRequestOperation;
import com.wiprohelp.helpindia.model.TrackRequestArray;
import com.wiprohelp.helpindia.model.TrackRequetElement;
import com.wiprohelp.helpindia.utilities.Constants;
import com.wiprohelp.helpindia.utilities.NetworkCheckBaseActivity;

import java.util.ArrayList;

public class TrackMyRequestView extends NetworkCheckBaseActivity {

    private ArrayList<TrackRequetElement> mDataset;
    private TrackMyRequestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_my_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataset = new ArrayList<TrackRequetElement>();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.track_request_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TrackMyRequestAdapter(mDataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void sendRequestToServer(){
        super.sendRequestToServer();
        String victimMobile = getIntent().getExtras().getString(Constants.TRACK_REQUEST_MOBILE);
        TrackRequestOperation trackRequestOperation = new TrackRequestOperation(victimMobile);
        VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(trackRequestOperation);
    }

    @Subscribe
    public void serverResponse(TrackRequestArray data){
        mDataset.clear();
        mDataset.addAll(data.getData());
        mAdapter.notifyDataSetChanged();
    }

}
