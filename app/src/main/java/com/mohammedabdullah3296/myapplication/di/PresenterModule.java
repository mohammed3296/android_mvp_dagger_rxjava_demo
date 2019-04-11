package com.mohammedabdullah3296.myapplication.di;


import android.content.Context;


import com.mohammedabdullah3296.myapplication.Views.jobs.JobsPresenter;
import com.mohammedabdullah3296.myapplication.Views.splash.SplashPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


//this class created to put  providers for Presenters

@Module
public class PresenterModule {

    @Provides
    @Singleton
    SplashPresenter provideMainPresenter(Context context) {
        return new SplashPresenter(context);
    }
    @Provides
    @Singleton
    JobsPresenter provideJobsPresenter(Context context) {
        return new JobsPresenter(context);
    }
}

