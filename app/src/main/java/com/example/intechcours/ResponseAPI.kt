package com.example.intechcours

import com.google.gson.annotations.SerializedName

data class ResponseAPI(
    @SerializedName("page") val page : Int,
    @SerializedName("total_results") val totalResults : Int,
    @SerializedName("total_pages") val totalPage : Int,
    @SerializedName("results") val results : List<Movie>
)