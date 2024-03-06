package com.zogik.templateandroid

import com.zogik.core.BaseCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val apiClient: ApiClient) : BaseCall() {

    suspend fun getApi(): Flow<Result<Unit>> {
        return flow { }
//        response {
//            apiClient.getApi()
//        }
    }
}