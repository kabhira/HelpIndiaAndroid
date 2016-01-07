package com.wiprohelp.helpindia.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.Requests.LiveRequestOperation;
import com.wiprohelp.helpindia.model.LiveRequestArray;
import com.wiprohelp.helpindia.model.LiveRequestElement;
import com.wiprohelp.helpindia.utilities.NetworkCheckBaseActivity;

import java.util.ArrayList;

public class VolunteerActionView extends NetworkCheckBaseActivity {

    private ArrayList<LiveRequestElement> mDataset;
    private VolunteerActionAdapter mAdapter;

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
        LiveRequestOperation liveRequestOperation = new LiveRequestOperation();
        VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(liveRequestOperation);
    }

    @Subscribe
    public void serverResponse(LiveRequestArray data){
        mDataset.clear();
        mDataset.addAll(data.getData());
        mAdapter.notifyDataSetChanged();
    }

}
