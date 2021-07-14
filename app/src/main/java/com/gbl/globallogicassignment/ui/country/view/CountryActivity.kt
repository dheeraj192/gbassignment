package com.gbl.globallogicassignment.ui.country.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gbl.globallogicassignment.data.datasource.CountryDataSourceImpl
import com.gbl.globallogicassignment.databinding.ActivityMainBinding
import com.gbl.globallogicassignment.ui.country.adapter.CountryListAdaptor
import com.gbl.globallogicassignment.ui.country.viewmodel.CountryViewModel
import com.gbl.globallogicassignment.utils.Status
import com.gbl.globallogicassignment.utils.ViewModelFactory


class CountryActivity : AppCompatActivity() {
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var activityBinding: ActivityMainBinding
    private lateinit var countryAdapter: CountryListAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        initView()
        initViewModel()
        initObservers()
    }

    private fun initView() {
        countryAdapter = CountryListAdaptor(arrayListOf())
        activityBinding.countryList.adapter = countryAdapter
        activityBinding.countryList.addItemDecoration(
            DividerItemDecoration(
                activityBinding.countryList.context,
                (activityBinding.countryList.layoutManager as LinearLayoutManager).orientation
            )
        )
    }

    private fun initObservers() {
        countryViewModel.getCountryLiveData().observe(
            this,
            {
                it?.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            activityBinding.progress.visibility = View.GONE
                            activityBinding.countryList.visibility = View.VISIBLE
                            it.data?.let { countries ->
                                countryAdapter.updateList(countries)
                            }
                        }
                        Status.LOADING -> {
                            activityBinding.progress.visibility = View.VISIBLE
                            activityBinding.countryList.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            activityBinding.progress.visibility = View.GONE
                            activityBinding.countryList.visibility = View.GONE
                            activityBinding.txtEmpty.visibility = View.VISIBLE
                        }
                    }
                }
            }
        )
        countryViewModel.fetchCountries()
    }

    private fun initViewModel() {
        countryViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(CountryDataSourceImpl())
            ).get(CountryViewModel::class.java)
    }
}