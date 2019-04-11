package com.mohammedabdullah3296.myapplication.Views.jobs;

import android.view.View;

public interface JobItemClick {

    void deleteJob(View v, int position);
    void updateJob(View v, int position);
}
