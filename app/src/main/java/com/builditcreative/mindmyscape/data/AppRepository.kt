package com.builditcreative.mindmyscape.data

import retrofit2.Response

class AppRepository(private val appApi: AppApi) {

    suspend fun getTherapies(): Response<GetTherapiesResultModel> {
        return appApi.getTherapies()
    }

}
