package com.gbl.globallogicassignment.data.datasource

import com.gbl.globallogicassignment.data.models.Country

interface CountryDataSource {
    /**
     * function to fetch list of countries
     */
    suspend fun fetchCountries(): List<Country>
}