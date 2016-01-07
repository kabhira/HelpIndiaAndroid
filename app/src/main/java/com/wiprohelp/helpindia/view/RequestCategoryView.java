package com.wiprohelp.helpindia.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.Requests.RequestCategoryOperation;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.model.RequestCategoryElement;
import com.wiprohelp.helpindia.model.RequestCategoryArray;
import com.wiprohelp.helpindia.utilities.NetworkCheckBaseActivity;
import com.wiprohelp.helpindia.utilities.CustomApplication;

import java.util.ArrayList;

public class RequestCategoryView extends NetworkCheckBaseActivity {

    private ArrayList<RequestCategoryElement> mDataset;
    private RequestCategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_catagory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.request_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDataset = new ArrayList<RequestCategoryElement>();
        mAdapter = new RequestCategoryAdapter(mDataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void sendRequestToServer(){
        super.sendRequestToServer();
        RequestCategoryOperation requestCategoryOperation = new RequestCategoryOperation();
        VolleyNetwork.getInstance(CustomApplication.getmContext()).addToRequestQueue(requestCategoryOperation);
    }

    @Subscribe
    public void serverResponse(RequestCategoryArray data){
        mDataset.clear();
        mDataset.addAll(data.getData());
        mAdapter.notifyDataSetChanged();
    }

}
