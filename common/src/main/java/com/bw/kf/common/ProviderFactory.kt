package com.bw.kf.common

import com.bw.kf.common.net.api.ApiService
import com.bw.kf.common.net.api.ApiServiceSign
import com.bw.kf.common.net.getServiceSign
import com.bw.kf.common.net.getServiceToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderFactory {

    @Provides
    @Singleton
    fun serviceSign(): ApiServiceSign = getServiceSign()

    @Provides
    @Singleton
    fun serviceToken(): ApiService = getServiceToken()


}