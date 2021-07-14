package com.gbl.globallogicassignment.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gbl.globallogicassignment.data.datasource.CountryDataSource
import com.gbl.globallogicassignment.ui.country.viewmodel.CountryViewModel

class ViewModelFactory(val countryDataSource: CountryDataSource) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            CountryViewModel(countryDataSource) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}