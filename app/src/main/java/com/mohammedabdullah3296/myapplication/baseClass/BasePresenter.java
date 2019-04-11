package com.mohammedabdullah3296.myapplication.baseClass;


// this class created to be base for each presenter in all application
public interface BasePresenter<T>  {
    // empty function for attach view
    void onAttach(T view);
    // empty function for Detach view
    void onDetach();
}
