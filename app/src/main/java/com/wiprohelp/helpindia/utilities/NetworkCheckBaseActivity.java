package com.wiprohelp.helpindia.utilities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wiprohelp.helpindia.Network.NetworkCheck;
import com.wiprohelp.helpindia.R;
import com.wiprohelp.helpindia.events.EventBusSingleton;

/**
 * Created by AB335009 on 12/30/2015.
 */
public class NetworkCheckBaseActivity extends AppCompatActivity  implements NetworkCheck.NetworkCheckInterface {

    private View networkCheckView;
    private NetworkCheck networkCheck;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        sendRequestToServer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBusSingleton.instance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBusSingleton.instance().unregister(this);
    }

    public void retryConnection(){
        sendRequestToServer();
    }

    protected void sendRequestToServer(){
        networkCheckView = findViewById(R.id.connection_layout);
        networkCheck = new NetworkCheck(this, networkCheckView);
        networkCheck.setNetworkCheckInterface(this);
    }
}
