package com.wiprohelp.helpindia.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.model.DataManager;
import com.wiprohelp.helpindia.model.RequestCategoryElement;
import com.wiprohelp.helpindia.model.TrackRequetElement;

import java.util.HashMap;
import java.util.List;

/**
 * Created by AB335009 on 12/23/2015.
 */
public class TrackMyRequestAdapter extends RecyclerView.Adapter<TrackMyRequestAdapter.ViewHolder> {
    private List<TrackRequetElement> mDataset;
    private Context mActivity;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mRequestCategory;
        public NetworkImageView mRequestCategoryImage;
        public TextView mVolunteerName;
        public TextView mVolunteerSpeciality;
        public TextView mVolunteerMobile;
        public TextView mVolunteerTime;
        public RelativeLayout middleBar;
        public RelativeLayout lowerBar;
        public LinearLayout responseAwaitedLayout;
        public ViewHolder(View v) {
            super(v);
            mRequestCategory = (TextView) v.findViewById(R.id.cell_track_request_cat_name);
            mRequestCategoryImage = (NetworkImageView) v.findViewById(R.id.cell_track_request_cat_image);
            mVolunteerName = (TextView) v.findViewById(R.id.cell_track_request_volunteer_name);
            mVolunteerSpeciality = (TextView) v.findViewById(R.id.cell_track_request_volunteer_speciality);
            mVolunteerMobile = (TextView) v.findViewById(R.id.cell_track_request_volunteer_mobile);
            mVolunteerTime = (TextView) v.findViewById(R.id.cell_track_request_volunteer_time);
            middleBar = (RelativeLayout) v.findViewById(R.id.middle_bar);
            lowerBar = (RelativeLayout) v.findViewById(R.id.lower_bar);
            responseAwaitedLayout = (LinearLayout) v.findViewById(R.id.response_awaited_layout);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(BaseListAdapter.this.mActivity, DetailNews.class);
//            intent.putExtra("NewsData", mDataset.get(getAdapterPosition()));
//            BaseListAdapter.this.mActivity.startActivity(intent);
        }
    }

    public TrackMyRequestAdapter(List<TrackRequetElement> myDataset, Activity activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrackMyRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_track_my_request, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader imageLoader = VolleyNetwork.getInstance(mActivity).getImageLoader();
        HashMap<String, RequestCategoryElement> categoryMap = DataManager.instance().getRequestCategoryMap();
        RequestCategoryElement categoryElement = categoryMap.get(mDataset.get(position).getCategoryId());
        holder.mRequestCategory.setText(categoryElement.getCategoryName());
        holder.mRequestCategoryImage.setImageUrl(categoryElement.getCategoryIMg(), imageLoader);
        holder.mVolunteerName.setText(mDataset.get(position).getVolunteerName());
        holder.mVolunteerSpeciality.setText(mDataset.get(position).getVolunteerSpeciality());
        holder.mVolunteerMobile.setText(mDataset.get(position).getVolenteerMobileNumber());
        holder.mVolunteerTime.setText(mDataset.get(position).getVolunteerExpectedTime());

        if(mDataset.get(position).getRequestStatus().equalsIgnoreCase("OPEN"))
            setOpenLayout(holder);
        else
            setAwaitedLayout(holder);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void setOpenLayout(ViewHolder h){
        h.middleBar.setVisibility(View.GONE);
        h.lowerBar.setVisibility(View.GONE);
        h.responseAwaitedLayout.setVisibility(View.VISIBLE);
    }

    private void setAwaitedLayout(ViewHolder h){
        h.middleBar.setVisibility(View.VISIBLE);
        h.lowerBar.setVisibility(View.VISIBLE);
        h.responseAwaitedLayout.setVisibility(View.GONE);
    }
}
