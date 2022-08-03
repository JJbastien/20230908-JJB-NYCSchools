package com.example.nycSchools.model

import com.google.gson.annotations.SerializedName

//data keys retrieve from sat score endpoint from api
data class NYCScore(
    val dbn: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("num_of_sat_test_takers")
    val numOfSatTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String,
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String,
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String

)
