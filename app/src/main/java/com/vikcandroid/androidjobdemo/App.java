package com.vikcandroid.androidjobdemo;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.evernote.android.job.JobManager;

/**
 * Created by android-dev on 10/3/17.
 */

public class App extends Application {

    public static final String TAG = App.class.getSimpleName();
    private static App mInstance;
    private RequestQueue mRequestQueue;

    public static synchronized App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        JobManager.create(this).addJobCreator(new DemoJobCreator());
//        JobManager.instance().getConfig().setAllowSmallerIntervalsForMarshmallow(true);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
