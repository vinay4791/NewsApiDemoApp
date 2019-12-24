package com.businessbooks.androidapp.api

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import vinay.com.newsapidemoapp.model.HeadlinesModelClass


interface ApiInterface {

//everything?q=apple&from=2019-12-20&to=2019-12-20&sortBy=popularity&apiKey=10bba9da1f87405588cac255526c4f76

    @GET("everything?q=apple&from=2019-12-20&to=2019-12-20&sortBy=popularity&apiKey=10bba9da1f87405588cac255526c4f76")
    fun getHeadlinesList(): Observable<Response<HeadlinesModelClass>>

}