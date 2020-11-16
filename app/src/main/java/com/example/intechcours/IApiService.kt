package com.example.intechcours

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey : String,@Query("query") titleMovie : String): Observable<ResponseAPI>

}