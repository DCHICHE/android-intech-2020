package com.example.intechcours

import io.reactivex.Observable
import java.util.*

object ApiService {
    private val apiService = RetrofitClient.getClient().create(IApiService::class.java)

    fun searchMovie(title : String) : Observable<ResponseAPI>{
        return apiService.searchMovie(BuildConfig.API_KEY,title)
    }
}