package com.paul.airpollutionassignment.data

import com.squareup.moshi.Json

data class AirData(
    val __extras: Extras,
    val _links: Links,
    val fields: List<Field>,
    val include_total: Boolean,
    val limit: String,
    val offset: String,
    val records: List<Record>,
    val resource_format: String,
    val resource_id: String,
    val total: String
)

data class Extras(
    val api_key: String
)

data class Links(
    val next: String,
    val start: String
)

data class Field(
    val id: String,
    val info: Info,
    val type: String
)

data class Record(
    val aqi: String,
    val co: String,
    val co_8hr: String,
    val county: String,
    val latitude: String,
    val longitude: String,
    val no: String,
    val no2: String,
    val nox: String,
    val o3: String,
    val o3_8hr: String,
    val pm10: String,
    val pm10_avg: String,
    @Json( name = "pm2.5") val pmTwoPointFive: String,
    @Json( name = "pm2.5_avg") val pmTwoPointFiveAvg: String,
    val pollutant: String,
    val publishtime: String,
    val siteid: String,
    val sitename: String,
    val so2: String,
    val so2_avg: String,
    val status: String,
    val wind_direc: String,
    val wind_speed: String
)

data class Info(
    val label: String
)