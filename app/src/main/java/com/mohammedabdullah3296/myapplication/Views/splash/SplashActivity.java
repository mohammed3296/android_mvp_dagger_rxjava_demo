package com.mohammedabdullah3296.myapplication.Views.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mohammedabdullah3296.myapplication.BuildConfig;
import com.mohammedabdullah3296.myapplication.R;
import com.mohammedabdullah3296.myapplication.Views.jobs.JobsActivity;
import com.mohammedabdullah3296.myapplication.data.models.getGovernors.Governor;
import com.mohammedabdullah3296.myapplication.di.DaggerApplication;
import com.mohammedabdullah3296.myapplication.helper.ConnectivityReceiver;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    SplashPresenter mainPresenter;
    ProgressBar progressBar;
    private TextView txtVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar2);
        ButterKnife.bind(this);
        //register some activity   for injections
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        mainPresenter.onAttach(this);
        mainPresenter.loadGovernor();
        txtVersion = findViewById(R.id.textView_version);
        txtVersion.setText("© Mohammed Elamary " + BuildConfig.VERSION_NAME);

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mainPresenter.checkConnection(isConnected);
    }

    @Override
    public void govSetProgress() {
        progressBar.setProgress(100, true);

    }

    @Override
    public void startLoginActivity() {
        startActivity(new Intent(this, JobsActivity.class));

    }

    @Override
    public void showMessage(String message, int mColor) {
    }

    @Override
    public void cashGovernorTable(ArrayList<Governor> governors) {
    }

    @Override
    public void onAttache() {
    }

    @Override
    public void onDetach() {
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}