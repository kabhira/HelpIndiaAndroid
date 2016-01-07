package com.wiprohelp.helpindia.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public TextView mVolunteerMobile;
        public ViewHolder(View v) {
            super(v);
            mRequestCategory = (TextView) v.findViewById(R.id.cell_track_request_cat_name);
            mRequestCategoryImage = (NetworkImageView) v.findViewById(R.id.cell_track_request_cat_image);
            mVolunteerName = (TextView) v.findViewById(R.id.cell_track_request_volunteer_name);
            mVolunteerMobile = (TextView) v.findViewById(R.id.cell_track_request_volunteer_mobile);
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
        HashMap<String, RequestCategoryElement> categoryMap = DataManager.instance().getRequestCategoryList();
        RequestCategoryElement categoryElement = categoryMap.get(mDataset.get(position).getCategory());
        holder.mRequestCategory.setText(categoryElement.getCatName());
        holder.mRequestCategoryImage.setImageUrl(categoryElement.getImage(), imageLoader);
        holder.mVolunteerName.setText(mDataset.get(position).getVolunteerName());
        holder.mVolunteerMobile.setText(mDataset.get(position).getMobile());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
