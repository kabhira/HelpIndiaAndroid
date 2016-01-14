package com.wiprohelp.helpindia.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.events.ConnectionStartedEvent;
import com.wiprohelp.helpindia.events.ConnectionSuccessfulEvent;
import com.wiprohelp.helpindia.events.DataVolleyError;
import com.wiprohelp.helpindia.events.EventBusSingleton;
import com.wiprohelp.helpindia.events.NoInternetConnectionEvent;
import com.wiprohelp.helpindia.events.ResponseStatusFailedEvent;

/**
 * Created by AB335009 on 12/24/2015.
 */
public class NetworkCheck implements View.OnClickListener {

    private NetworkCheckInterface networkCheckInterface;
    private Context mContext;
    private LinearLayout noConnectionLayout;
    private LinearLayout serviceErrorLayout;
    private ProgressBar mProgressBar;
    private Button retryButton;
    private Button errorRetryButton;
    private TextView networkErrorMsg;

    public NetworkCheck(Context context, View connectionView){
        mContext = context;
        noConnectionLayout = (LinearLayout) connectionView.findViewById(R.id.no_connection_layout);
        serviceErrorLayout = (LinearLayout) connectionView.findViewById(R.id.service_error_layout);
        mProgressBar = (ProgressBar) connectionView.findViewById(R.id.connection_layout_progressBar);
        retryButton = (Button) connectionView.findViewById(R.id.connection_layout_retry_button);
        errorRetryButton = (Button) connectionView.findViewById(R.id.connection_layout_error_retry_button);
        networkErrorMsg = (TextView) connectionView.findViewById(R.id.network_error_msg);
        retryButton.setOnClickListener(this);
        errorRetryButton.setOnClickListener(this);
        EventBusSingleton.instance().register(this);
    }

    @Subscribe
    public void noInternetConnectionEvent(NoInternetConnectionEvent event){
        setNoConnectionView();
    }

    @Subscribe
    public void connectionStartedEvent(ConnectionStartedEvent event){
        setProgressView();
    }

    @Subscribe
    public void connectionSuccessfulEvent(ConnectionSuccessfulEvent event){
        setConnectionSuccessful();
    }

    @Subscribe
    public void volleyError(VolleyError event){
        setServiceError();
    }

    @Subscribe
    public void responseStatusFailedEvent(ResponseStatusFailedEvent event){
        setServiceFailedResponse();
    }

    private void setServiceError(){
        noConnectionLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        serviceErrorLayout.setVisibility(View.VISIBLE);

        networkErrorMsg.setText(mContext.getResources().getString(R.string.network_error_string));
    }

    private void setServiceFailedResponse(){
        noConnectionLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        serviceErrorLayout.setVisibility(View.VISIBLE);

        networkErrorMsg.setText(mContext.getResources().getString(R.string.network_response_error_string));
    }

    private void setNoConnectionView(){
        noConnectionLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        serviceErrorLayout.setVisibility(View.INVISIBLE);
    }

    private void setProgressView(){
        noConnectionLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        serviceErrorLayout.setVisibility(View.INVISIBLE);
    }

    private void setConnectionSuccessful(){
        noConnectionLayout.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        serviceErrorLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.connection_layout_retry_button:
                if(networkCheckInterface != null){
                    networkCheckInterface.retryConnection();
                }
                break;
            case R.id.connection_layout_error_retry_button:
                if(networkCheckInterface != null){
                    networkCheckInterface.retryConnection();
                }
                break;
        }
    }

    public interface NetworkCheckInterface{
        public void retryConnection();
    }

    public void setNetworkCheckInterface(NetworkCheckInterface networkCheckInterface){
        this.networkCheckInterface = networkCheckInterface;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        EventBusSingleton.instance().unregister(this);
    }
}
