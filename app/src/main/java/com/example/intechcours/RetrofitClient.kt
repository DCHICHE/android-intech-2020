package com.example.intechcours

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null;

    private fun getOkHttpClientDebug() : OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return  OkHttpClient().newBuilder()
            .cache(null)
            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(ApiInterceptor())
            .addNetworkInterceptor{ chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .build()
                )
            }
            .callTimeout(2,TimeUnit.MINUTES)
            .writeTimeout(2,TimeUnit.MINUTES)
            .readTimeout(2,TimeUnit.MINUTES)
            .connectTimeout(2,TimeUnit.MINUTES)
            .build()
    }


    fun getClient() : Retrofit{
        if(retrofit == null){
            if(BuildConfig.DEBUG){
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClientDebug())
                    .build()
            }
        }
        return retrofit!!;
    }
}