package com.vikcandroid.androidjobdemo;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by android-dev on 10/3/17.
 */

public class DemoJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        switch (tag) {
            case ShowNotificationJob.TAG:
                return new ShowNotificationJob();
            default:
                return null;
        }

    }
}
