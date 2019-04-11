package com.mohammedabdullah3296.myapplication.Views.jobs;


import com.mohammedabdullah3296.myapplication.baseClass.BaseView;
import com.mohammedabdullah3296.myapplication.data.models.jops.Job;
import com.mohammedabdullah3296.myapplication.data.models.positions.PositionResponseDetails;

import java.util.ArrayList;

public interface JobsView extends BaseView {

    void showProgress();
    void hideProgress();
    void startRoomActivity();
    void afterDelete(ArrayList<Job> jobs);
    void afterUpdate(ArrayList<Job> jobs);
    void showMessage(String message, int mColor);
    void getJobs(ArrayList<Job> jobs);
    void afterAdd(ArrayList<Job> jobs);
    void getPostionsNames(ArrayList<PositionResponseDetails> jobs);
}
