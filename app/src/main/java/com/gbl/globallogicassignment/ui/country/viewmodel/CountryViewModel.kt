package com.gbl.globallogicassignment.ui.country.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.globallogicassignment.data.datasource.CountryDataSource
import com.gbl.globallogicassignment.data.models.Country
import com.gbl.globallogicassignment.utils.Resource
import kotlinx.coroutines.launch

class CountryViewModel(private val countryDataSource: CountryDataSource) : ViewModel() {

    private val countryLiveData: MutableLiveData<Resource<List<Country>>> = MutableLiveData()

    fun getCountryLiveData(): MutableLiveData<Resource<List<Country>>> {
        return countryLiveData
    }

    fun fetchCountries() {
        //Used resource here so that if there any data source from network or db then based on that
        //the loading and showing progress dialog and error message can be done in future
        countryLiveData.value = Resource.loading(null)
        viewModelScope.launch {
            try {
                val list = countryDataSource.fetchCountries()
                countryLiveData.value = Resource.success(list)
            } catch (e: Exception) {
                countryLiveData.value = Resource.error(null,"Error while fetching countries")
            }
        }
    }

}