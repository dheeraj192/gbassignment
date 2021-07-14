package com.gbl.globallogicassignment.data.datasource

import com.gbl.globallogicassignment.data.models.Country
import kotlinx.coroutines.delay

class CountryDataSourceImpl : CountryDataSource {
    override suspend fun fetchCountries(): List<Country> {
        //Added dealy so that spinner is visible
        delay(2000)
        return getCountryList()
    }

    private fun getCountryList(): List<Country> {
        val countryList = mutableListOf<Country>()
        countryList.add(Country("USA", "country in North America"))
        countryList.add(Country("Australia", "country in Australia"))
        countryList.add(Country("Egypt", "An Ancient country"))
        countryList.add(Country("India", "A diverse country"))
        countryList.add(Country("England", "Part of United Kingdom"))
        return countryList
    }
}