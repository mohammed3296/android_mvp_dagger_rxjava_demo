package com.mohammedabdullah3296.myapplication.di;

import android.app.Application;
import android.content.Context;

import com.mohammedabdullah3296.myapplication.data.LocalSqlite.ItemDbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


//this class created to put main providers an all application
@Module
public class AppModule {
    private Application application;


    //provide  application reference
    public AppModule(Application application) {
        this.application = application;
    }


    //provide  context
    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }


    //provide  item helper for sqllit
    @Provides
    @Singleton
    public ItemDbHelper provideItemHelper(Context context) {
        return new ItemDbHelper(context);
    }







}
