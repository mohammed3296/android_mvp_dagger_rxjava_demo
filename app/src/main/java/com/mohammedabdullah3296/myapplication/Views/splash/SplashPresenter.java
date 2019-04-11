package com.mohammedabdullah3296.myapplication.Views.splash;

import android.content.Context;

import com.mohammedabdullah3296.myapplication.baseClass.BasePresenter;
import com.mohammedabdullah3296.myapplication.data.ApisClient.ApiInterface;
import com.mohammedabdullah3296.myapplication.data.LocalSqlite.ItemDbHelper;
import com.mohammedabdullah3296.myapplication.data.models.getGovernors.GetGovernorsResponse;
import com.mohammedabdullah3296.myapplication.data.models.getGovernors.Governor;
import com.mohammedabdullah3296.myapplication.di.DaggerApplication;
import com.mohammedabdullah3296.myapplication.helper.Utilities;


import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashPresenter implements BasePresenter<SplashView> {
    SplashView mView;
    boolean isLoaded = false;

    //inject api interface object
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    // create sqllit reference
    @Inject
    ItemDbHelper mItemDbHelper;
    @Override
    public void onAttach(SplashView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public SplashPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void loadGovernor() {
        if(!Utilities.checkConnection(mContext)){

            checkConnection(false);

            return;
        }


      //  mView.showLoading();
        mApiInterface.getGovernors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetGovernorsResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.govSetProgress();
                      mView.startLoginActivity();
                    }
                    @Override
                    public final void onError(Throwable e) {

                    }
                    @Override
                    public final void onNext(GetGovernorsResponse response) {
                        isLoaded = true;
                        mView.cashGovernorTable(response.getGovernors());
                        if (!mItemDbHelper.getGovernors().isEmpty())
                        mItemDbHelper.deleteSavedGovernors();

                        saveGovernors(response.getGovernors());
                    }
                });

    }



    //store Realm data
    private void saveGovernors(ArrayList<Governor> governors) {
        mItemDbHelper.addGovernors(governors);
    }

    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            loadGovernor();
            isLoaded = false;
         //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }

        if(!isConnected){
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
               //
                    //mView.startLoginActivity();
                }
            } , 1000);

        }
        else if(!isConnected && isLoaded){
            //offline check and  data loaded
          //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        }
        else if(!isConnected && !isLoaded){
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
       //     mView.updateList(mItemDbHelper.getAllItems());

        }else if(isConnected && isLoaded){
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }

}
