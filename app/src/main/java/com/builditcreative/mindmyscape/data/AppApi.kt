package com.builditcreative.mindmyscape.data

import retrofit2.Response
import retrofit2.http.GET

interface AppApi {

    @JvmSuppressWildcards
    @GET("doctor/therapies/")
    suspend fun getTherapies(): Response<GetTherapiesResultModel>

}
