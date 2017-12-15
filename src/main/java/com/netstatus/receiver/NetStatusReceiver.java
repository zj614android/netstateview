package com.netstatus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.netstatus.NetWorkUtil;
import com.netstatus.NetworkStateView;

/**
 * Created by thinkpad on 2017/12/14.
 */

public class NetStatusReceiver extends BroadcastReceiver {

    private Context mContext = null;
    private NetworkStateView mNetworkStateView = null;

    public NetStatusReceiver(Context mContext, NetworkStateView mNetworkStateView) {
        this.mContext = mContext;
        this.mNetworkStateView = mNetworkStateView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean networkConnected = NetWorkUtil.isNetworkConnected(mContext);
        int netWorkState = -1;

        if (networkConnected) {
            netWorkState = NetWorkUtil.NETWORK_OK;
        } else {
            netWorkState = NetWorkUtil.NETWORK_NONE;
        }

        Log.e("netStatus", "   onReceive  >>>  " + netWorkState);

        if (null != mNetworkStateView) {
            if (netWorkState == -1) {
                Log.e("netStatus", "     showNoNetwork");
                mNetworkStateView.showNoNetwork();
            } else {
                if (null != mNetworkStateView) {
                    try {
                        int lastStatus = (int) mNetworkStateView.getTag();
                        switch (lastStatus) {
                            case NetWorkUtil.NETWORK_STATUS_EMPTY:
                                Log.e("netStatus", "     showEmpty");
                                mNetworkStateView.showEmpty();
                                break;
                            case NetWorkUtil.NETWORK_STATUS_ERROR:
                                Log.e("netStatus", "     showError");
                                mNetworkStateView.showError();
                                break;
                            case NetWorkUtil.NETWORK_STATUS_LOADING:
                                Log.e("netStatus", "     showLoading");
                                mNetworkStateView.showLoading();
                                break;
                            case NetWorkUtil.NETWORK_STATUS_NONETWORK:
                            case NetWorkUtil.NETWORK_STATUS_SUCCESS:
                            default:
                                Log.e("netStatus", "     showSuccess");
                                mNetworkStateView.showSuccess();
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
