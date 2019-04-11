package com.mohammedabdullah3296.myapplication.Views.splash;


import com.mohammedabdullah3296.myapplication.baseClass.BaseView;
import com.mohammedabdullah3296.myapplication.data.models.getGovernors.Governor;

import java.util.ArrayList;


//class created for register function for main view
public interface SplashView extends BaseView {
    void govSetProgress();
    void startLoginActivity();
    void showMessage(String message, int mColor);
    void cashGovernorTable(ArrayList<Governor> governors);
}
