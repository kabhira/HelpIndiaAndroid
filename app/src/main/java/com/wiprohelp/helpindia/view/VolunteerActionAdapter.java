package com.wiprohelp.helpindia.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.Requests.HelpRequestUpdateOperation;
import com.wiprohelp.helpindia.Requests.VolunteerRegistrationOperation;
import com.wiprohelp.helpindia.model.DataManager;
import com.wiprohelp.helpindia.model.RequestCategoryElement;
import com.wiprohelp.helpindia.model.LiveRequestElement;
import com.wiprohelp.helpindia.utilities.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ab335009 on 1/6/2016.
 */
public class VolunteerActionAdapter extends RecyclerView.Adapter<VolunteerActionAdapter.ViewHolder> {
    private List<LiveRequestElement> mDataset;
    private Context mActivity;
    private static String TAG = "VolunteerActionAdapter";

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mProblem;
        public TextView mSeekerName;
        public TextView mSeekerMobile;
        public TextView mAddress;
        public TextView mExpectedTime;
        public TextView mAcceptButton;
        public ViewHolder(View v) {
            super(v);
            mProblem = (TextView) v.findViewById(R.id.cell_volunteer_action_problem);
            mSeekerName = (TextView) v.findViewById(R.id.cell_volunteer_action_name);
            mSeekerMobile = (TextView) v.findViewById(R.id.cell_volunteer_action_mobile);
            mAddress = (TextView) v.findViewById(R.id.cell_volunteer_action_address);
            mExpectedTime = (TextView) v.findViewById(R.id.cell_volunteer_action_expected_time);
            mAcceptButton = (Button) v.findViewById(R.id.cell_volunteer_action_accept);
            mAcceptButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ((VolunteerActionView)mActivity).sendStatusRequestUpdateToServer(mDataset.get(getAdapterPosition()));
        }
    }

    public VolunteerActionAdapter(List<LiveRequestElement> myDataset, Activity activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VolunteerActionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_volunteer_action, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String, RequestCategoryElement> categoryMap = DataManager.instance().getRequestCategoryMap();
        RequestCategoryElement categoryElement = categoryMap.get(mDataset.get(position).getCategoryId());
        holder.mProblem.setText(categoryElement.getCategoryName());
        holder.mSeekerName.setText(mDataset.get(position).getVictimName());
        holder.mSeekerMobile.setText(mDataset.get(position).getVictimPhoneNumber());
        holder.mAddress.setText(mDataset.get(position).getVictimAddress());
        holder.mExpectedTime.setText(mDataset.get(position).getVictimExpectedTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
