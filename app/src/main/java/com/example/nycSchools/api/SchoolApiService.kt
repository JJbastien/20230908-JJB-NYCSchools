package com.example.nycSchools.api

import com.example.nycSchools.model.NYCSchool
import com.example.nycSchools.model.NYCScore
import com.example.nycSchools.utils.SCHOOL_ENDPOINT
import com.example.nycSchools.utils.SCORE_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//interface that provides Https functions. We only need the GET function in this case

interface SchoolApiService {
    @GET(SCHOOL_ENDPOINT)
    suspend fun fetchNYCSchools(): Response<List<NYCSchool>>

    @GET(SCORE_ENDPOINT)
    suspend fun fetchNYCScore(
        @Query("dbn") dbn: String
    ): Response<List<NYCScore>>
}