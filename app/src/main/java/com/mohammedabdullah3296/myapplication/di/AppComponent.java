package com.mohammedabdullah3296.myapplication.di;


import com.mohammedabdullah3296.myapplication.Views.jobs.JobsActivity;
import com.mohammedabdullah3296.myapplication.Views.jobs.JobsPresenter;
import com.mohammedabdullah3296.myapplication.Views.splash.SplashActivity;
import com.mohammedabdullah3296.myapplication.Views.splash.SplashPresenter;

import javax.inject.Singleton;

import dagger.Component;

// this class created for register who need inject
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class,PresenterModule.class})
public interface AppComponent {

    //register splash activity it will need objects for injection
    void inject(SplashActivity splashActivity);

    void inject(SplashPresenter mainPresenter);

    //survey By  Mohammed Elamary
    void inject(JobsActivity jobsActivity);

    void inject(JobsPresenter jobsPresenter);
}
