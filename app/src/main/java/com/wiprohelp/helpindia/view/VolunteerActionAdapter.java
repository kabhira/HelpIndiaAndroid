package com.wiprohelp.helpindia.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.model.DataManager;
import com.wiprohelp.helpindia.model.RequestCategoryElement;
import com.wiprohelp.helpindia.model.LiveRequestElement;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ab335009 on 1/6/2016.
 */
public class VolunteerActionAdapter extends RecyclerView.Adapter<VolunteerActionAdapter.ViewHolder> {
    private List<LiveRequestElement> mDataset;
    private Context mActivity;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mProblem;
        public TextView mSeekerMobile;
        public TextView mAddress;
        public TextView mExpectedTime;
        public ViewHolder(View v) {
            super(v);
            mProblem = (TextView) v.findViewById(R.id.cell_volunteer_action_problem);
            mSeekerMobile = (TextView) v.findViewById(R.id.cell_volunteer_action_mobile);
            mAddress = (TextView) v.findViewById(R.id.cell_volunteer_action_address);
            mExpectedTime = (TextView) v.findViewById(R.id.cell_volunteer_action_expected_time);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(BaseListAdapter.this.mActivity, DetailNews.class);
//            intent.putExtra("NewsData", mDataset.get(getAdapterPosition()));
//            BaseListAdapter.this.mActivity.startActivity(intent);
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
        HashMap<String, RequestCategoryElement> categoryMap = DataManager.instance().getRequestCategoryList();
        RequestCategoryElement categoryElement = categoryMap.get(mDataset.get(position).getCatagoryId());
        holder.mProblem.setText(categoryElement.getCatName());
        holder.mSeekerMobile.setText(mDataset.get(position).getSeekerMobile());
        holder.mAddress.setText(mDataset.get(position).getAddress());
        holder.mExpectedTime.setText(mDataset.get(position).getExpectedTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
