package com.paul.airpollutionassignment.network

import com.paul.airpollutionassignment.data.AirData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://data.epa.gov.tw/api/v2/"


private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = when (true) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    )
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface AirApiService {


    @GET("aqx_p_432")
    suspend fun getAirPollution(
        @Query("api_key") authorization: String,
        @Query("limit") locationName: Int,
    ): AirData

}


object AirApi {
    val retrofitService: AirApiService by lazy { retrofit.create(AirApiService::class.java) }
}