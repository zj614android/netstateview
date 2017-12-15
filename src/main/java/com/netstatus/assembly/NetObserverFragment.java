package com.netstatus.assembly;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.netstatus.NetLinearWrapContainer;
import com.netstatus.NetworkStateView;
import com.netstatus.receiver.NetStatusReceiver;
import com.wolvesstateview.R;


public class NetObserverFragment extends Fragment implements NetworkStateView.OnRefreshListener{

    protected NetworkStateView mNetworkStateView = null;
    private boolean onceFlag = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 初始化网络状态
     *
     * @param rootView
     */
    protected void initNetState(final View rootView) {
        ViewTreeObserver vto = rootView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (onceFlag) {
                    try {
                        NetLinearWrapContainer rv = (NetLinearWrapContainer) rootView;

                        final NetworkStateView nsvStateView = (NetworkStateView) rv.findViewById(R.id.nsv_state_view);

                        initNetReceiver(nsvStateView);

//                        nsvStateView.showLoading();
//                        nsvStateView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                nsvStateView.showSuccess();
//                            }
//                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    onceFlag = false;
                }
            }
        });
    }

    /**
     * 初始化网络状态的广播
     * @param nsvStateView
     */
    private void initNetReceiver(NetworkStateView nsvStateView) {
        this.mNetworkStateView = nsvStateView;
        this.mNetworkStateView.setOnRefreshListener(this);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        NetStatusReceiver netStatusReceiver = new NetStatusReceiver(getActivity(), mNetworkStateView);
        getActivity().registerReceiver(netStatusReceiver, intentFilter);
    }



    @Override
    public void netWorkStatusEmptyRefresh() {
    }

    @Override
    public void netWorkStatusErrorRefresh() {

    }

    @Override
    public void netWorkStatusNoNetWorkRefresh() {

    }

    /**
     * NetStatus相关
     */
    protected void showloading(){
        if(null != mNetworkStateView){
            mNetworkStateView.showLoading();
        }else {
            Log.e("NetState","mNetworkStateView为空");
        }
    }


    /**
     * NetStatus相关
     */
    protected void showEmpty(){
        if(null != mNetworkStateView){
            mNetworkStateView.showEmpty();
        }else {
            Log.e("NetState","mNetworkStateView为空");
        }
    }


    /**
     * NetStatus相关
     */
    protected void showSuccess(){
        if(null != mNetworkStateView){
            mNetworkStateView.showSuccess();
        }else {
            Log.e("NetState","mNetworkStateView为空");
        }
    }


    /**
     * NetStatus相关
     */
    protected void showNoNet(){
        if(null != mNetworkStateView){
            mNetworkStateView.showNoNetwork();
        }else {
            Log.e("NetState","mNetworkStateView为空");
        }
    }

    /**
     * NetStatus相关
     */
    protected void showError(){
        if(null != mNetworkStateView){
            mNetworkStateView.showError();
        }else {
            Log.e("NetState","mNetworkStateView为空");
        }
    }
}
