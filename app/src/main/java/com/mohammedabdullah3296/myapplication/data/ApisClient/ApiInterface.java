package com.mohammedabdullah3296.myapplication.data.ApisClient;


import com.mohammedabdullah3296.myapplication.data.models.getGovernors.GetGovernorsResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {

    //By Elamary
    @GET(EndPoints.GET_GOVERNOR)
    Observable<GetGovernorsResponse> getGovernors();

}
