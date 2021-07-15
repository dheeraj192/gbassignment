package com.gbl.globallogicassignment.ui.country.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gbl.globallogicassignment.testutil.CoroutineTestRules
import com.gbl.globallogicassignment.data.datasource.CountryDataSource
import com.gbl.globallogicassignment.data.models.Country
import com.gbl.globallogicassignment.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryViewModelTest {

    @get:Rule
    val testCoroutineRule = CoroutineTestRules()

    @get:Rule
    val testInstantRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var countryDataSource: CountryDataSource

    @Mock
    private lateinit var countryLiveDataObserver: Observer<Resource<List<Country>>>

    @Test
    fun fetchCountries_successTest() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Country>())
                .`when`(countryDataSource)
                .fetchCountries()
            val viewModel = CountryViewModel(countryDataSource)
            viewModel.getCountryLiveData().observeForever(countryLiveDataObserver)
            viewModel.fetchCountries()
            verify(countryDataSource).fetchCountries()
            verify(countryLiveDataObserver).onChanged(Resource.success(emptyList()))
            viewModel.getCountryLiveData().removeObserver(countryLiveDataObserver)
        }
    }

    @Test
    fun fetchCountries_errorTest() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error while fetching countries"
            doThrow(RuntimeException(errorMessage))
                .`when`(countryDataSource)
                .fetchCountries()
            val viewModel = CountryViewModel(countryDataSource)
            viewModel.getCountryLiveData().observeForever(countryLiveDataObserver)
            viewModel.fetchCountries()
            verify(countryDataSource).fetchCountries()
            verify(countryLiveDataObserver).onChanged(
                Resource.error(
                    null,
                    errorMessage
                )
            )
            viewModel.getCountryLiveData().removeObserver(countryLiveDataObserver)
        }
    }
}