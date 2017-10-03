package com.vikcandroid.androidjobdemo;

import android.app.Application;

import com.evernote.android.job.JobManager;

/**
 * Created by android-dev on 10/3/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new DemoJobCreator());
//        JobManager.instance().getConfig().setAllowSmallerIntervalsForMarshmallow(true);
    }
}
