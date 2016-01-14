package com.wiprohelp.helpindia.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.Requests.RequestCategoryOperation;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.model.DataManager;
import com.wiprohelp.helpindia.model.RequestCategoryElement;
import com.wiprohelp.helpindia.model.RequestCategoryArray;
import com.wiprohelp.helpindia.utilities.NetworkCheckBaseActivity;
import com.wiprohelp.helpindia.utilities.CustomApplication;

import java.util.ArrayList;

public class RequestCategoryView extends AppCompatActivity {

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
        mDataset.addAll(DataManager.instance().getRequestCategoryArray());
        mAdapter = new RequestCategoryAdapter(mDataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
