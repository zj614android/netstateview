package com.netstatus;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.wolvesstateview.R;

public class NetLinearWrapContainer extends LinearLayout {

    private Context mContext = null;
    private boolean onceFlag = true;
    private View mContentView;
    private boolean mHaveActionBarFlag;

    public NetLinearWrapContainer(Context context) {
        this(context, null);
    }

    public NetLinearWrapContainer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetLinearWrapContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mContentView = View.inflate(getContext(), R.layout.wrapper_network_state, this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetLinearWrapContainer);
        if (null != typedArray) {
            mHaveActionBarFlag = typedArray.getBoolean(R.styleable.NetLinearWrapContainer_haveActionBar, false);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (onceFlag) {
            com.netstatus.NetworkStateView inflateNetworkStateView = (com.netstatus.NetworkStateView) View.inflate(getContext(), R.layout.view_network_state, null);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            inflateNetworkStateView.setLayoutParams(layoutParams);
            Log.e("netStatus", "before  >>  " + getChildCount());
            if(mHaveActionBarFlag){
                addView(inflateNetworkStateView, 2);
            }else {
                addView(inflateNetworkStateView, 1);
            }
            Log.e("netStatus", "after  >>  " + getChildCount());
            onceFlag = false;
        }
    }
}
