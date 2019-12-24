package com.businessbooks.androidapp.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import vinay.com.newsapidemoapp.model.HeadlinesModelClass


class ServiceManager {
    var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface::class.java)
    }

    fun getHeadlinesList(): Observable<Response<HeadlinesModelClass>> {
        return apiInterface!!.getHeadlinesList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map({ response -> response })
    }
}
