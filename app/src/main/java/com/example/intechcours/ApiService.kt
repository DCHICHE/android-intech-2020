package com.example.intechcours

import io.reactivex.Observable
import org.intellij.lang.annotations.Language
import java.util.*

object ApiService {
    private val apiService = RetrofitClient.getClient().create(IApiService::class.java)

    fun searchMovie(title : String,language: String) : Observable<ResponseAPI>{
        return apiService.searchMovie(BuildConfig.API_KEY,title,language)
    }

    fun getSimilarMovie(movieId : Int,language: String) : Observable<ResponseAPI>{
        return apiService.getSimilarMovie(movieId,BuildConfig.API_KEY,language)
    }
}