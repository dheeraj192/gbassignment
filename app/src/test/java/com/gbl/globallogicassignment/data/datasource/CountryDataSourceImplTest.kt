package com.gbl.globallogicassignment.data.datasource

import com.gbl.globallogicassignment.testutil.CoroutineTestRules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryDataSourceImplTest {

    @get:Rule
    val testCoroutineRule = CoroutineTestRules()

    @Test
    fun fetchCountries() {
        testCoroutineRule.runBlockingTest {
            val countryDataSource = CountryDataSourceImpl()
            Assert.assertEquals(5, countryDataSource.fetchCountries().size)
        }

    }
}