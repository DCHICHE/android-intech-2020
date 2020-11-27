package com.example.intechcours

import io.reactivex.Observable
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiService {

    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey : String,@Query("query") titleMovie : String,@Query("language") language: String): Observable<ResponseAPI>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovie(@Path("movie_id") movieId : Int,@Query("api_key") apiKey : String): Observable<ResponseAPI>

}