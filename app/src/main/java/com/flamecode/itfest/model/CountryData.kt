package com.flamecode.itfest.model

/**
 * This model class is designated for parsing country data about Covid-19
 *
 * @author Iomava
 */
class CountryData(
    private val country: String,
    private val cases: Int,  val todayCases: Int,
     val deaths: Int, private val todayDeaths: Int,
     val recovered: Int,  val active: Int,
     val critical: Int,  val casesPerOneMillion: Int,
     val deathsPerOneMillion: Int,  val totalTests: Int,
     val testsPerOneMillion: Int
) {
}