package com.businessbooks.androidapp.api

object ServiceManagerProvider {
    fun provideSearchRepository(): ServiceManager {
        return ServiceManager()
    }
}