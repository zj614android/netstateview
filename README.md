# What？

**本库是基于该项目进行的二次封装：**
https://github.com/AlarmZeng/NetworkStateView

**这个库是集成了如下功能：**
- **保留基本库原有的使用方式（对应不同网络状态的布局【[参考](https://github.com/AlarmZeng/NetworkStateView)】）**
- **网络判断工具类**
- **网络状态变化的广播**
- **对应封装的逻辑基类**
- **包装类：NetLinearWrapContainer**

# Why？
**为何要封装？**
- 1.主要是工作中用到了多种网络状态切换，然后同事一看对我说，“你这用着不爽，每个类都要去include一下。不开心”。
- 2.将网络监测相关代码汇总到NetObserverActivity，NetObserverAppCompatActivity，NetObserverFragment，详见Demo。

# Dependencies！
- Step 1. Add it in your root build.gradle at the end of repositories:
```java
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```
- Step 2. Add the dependency:
```java
dependencies {
	        compile 'com.github.zj614android:netstateview:1.0.0'
}
```

若想修改源码时，可以：
```java
compile project(':netstateview')
```

# How？
## 基础使用方式：
[参考原项目](https://github.com/AlarmZeng/NetworkStateView)

**xml**
```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="demo.netstate.com.netstatedemo.LittleActivity">

    <include layout="@layout/view_network_state" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"
        android:text="突突突~突突~"
        android:textSize="30sp" />
</LinearLayout>
```

**java代码**
```java
package demo.netstate.com.netstatedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.netstatus.NetworkStateView;

public class LittleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little);
        com.netstatus.NetworkStateView nv = (NetworkStateView) findViewById(R.id.nsv_state_view);
        nv.showLoading();
    }
}
```

## NetLinearWrapContainer包装类的使用：
### 基本：
**xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.netstatus.NetLinearWrapContainer xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"
        android:text="突突突~突突~"
        android:textSize="30sp" />

</com.netstatus.NetLinearWrapContainer>
```

**java代码**
```java
package demo.netstate.com.netstatedemo;

import android.os.Bundle;

import demo.netstate.com.netstatedemo.base.BaseAppCompatActivity;


public class CommonActivity extends BaseAppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }
}
```

**NetLinearWrapContainer & extends BaseAppCompatActivit做了这些事：**
- 自动往根布局里添加状态视图，注意目前只支持vertical的NetLinearWrapContainer
- 自动注册网络监测
- 根据网络状态变化自动切换视图
- 支持自定义属性，是否拥有titleBar

这只是普通activity内的，还有fragment和带有actionbar的activity的使用方式。详细见demo

### 局限性：
根布局用这个组件进行布局的时候，由于他是一个LinearLayout，且只支持vertical。

## 三个包装类：
> NetObserverActivity.java
> NetObserverAppCompatActivity.java
> NetObserverFragment.java

这三个类是封装类，里边做了网络状态相关的初始化，具体怎么集成，可以看Demo内的Base基类是如何封装的。


### 点击按钮"重新加载"的点击事件设置：
可选的 **Override** 3个回调（你不想用可以不写）：
```java
    public interface OnRefreshListener {
        /**
         * 空的刷新按钮
         */
        void netWorkStatusEmptyRefresh();

        /**
         * 网络错误刷新按钮
         */
        void netWorkStatusErrorRefresh();

        /**
         * 没有网络刷新按钮
         */
        void netWorkStatusNoNetWorkRefresh();
    }
```

### 状态切换：
$NetObserverAppCompatActivity.java
```java
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


```
详见Demo


# Demo
![](https://wx3.sinaimg.cn/mw690/0061ejqJly1fmhb2986yng30810fz1kx.gif)![](https://wx3.sinaimg.cn/mw690/0061ejqJly1fmhb27x4jkg307y0fze2j.gif)

[GitHub项目地址](https://github.com/zj614android/netstateview)
[结合网络请求使用的Demo](https://github.com/zj614android/NetstatusTestWithPostNet)
[基本接入使用的Demo](https://github.com/zj614android/NetStateDemo)

**最后，后面我会补上实现细节，并发一个帖子，有错误的地方请直接指出，我会修正改进，感谢。**





