package com.wiprohelp.helpindia.view;

/**
 * Created by AB335009 on 12/22/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wiprohelp.helpindia.Network.VolleyNetwork;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.model.RequestCategoryElement;
import com.wiprohelp.helpindia.utilities.Constants;

import java.util.List;

public class RequestCategoryAdapter extends RecyclerView.Adapter<RequestCategoryAdapter.ViewHolder> {
    private List<RequestCategoryElement> mDataset;
    private Context mActivity;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;
        public NetworkImageView mImageView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.new_request_description);
            mImageView = (NetworkImageView) v.findViewById(R.id.new_request_imageView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mActivity, HelpRequestView.class);
            intent.putExtra(Constants.REQUEST_CONTACTID, mDataset.get(getAdapterPosition()).getCategoryId());
            mActivity.startActivity(intent);
        }
    }

    public RequestCategoryAdapter(List<RequestCategoryElement> myDataset, Activity activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RequestCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_request_catagory, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getCategoryName());
        ImageLoader imageLoader = VolleyNetwork.getInstance(mActivity).getImageLoader();
        holder.mImageView.setImageUrl(mDataset.get(position).getCategoryIMg(), imageLoader);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
