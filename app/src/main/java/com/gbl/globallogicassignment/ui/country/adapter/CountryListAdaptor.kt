package com.gbl.globallogicassignment.ui.country.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gbl.globallogicassignment.data.models.Country
import com.gbl.globallogicassignment.databinding.CountryItemBinding

class CountryListAdaptor(private val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryListAdaptor.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int = countryList.size


    fun updateList(newCountryList: List<Country>) {
        countryList.apply {
            clear()
            addAll(newCountryList)
        }
        notifyDataSetChanged()
    }

    class CountryViewHolder(countryItemBinding: CountryItemBinding) :
        RecyclerView.ViewHolder(countryItemBinding.root) {
        private val itemBinding: CountryItemBinding = countryItemBinding

        fun bind(country: Country) {
            itemBinding.country = country
        }
    }
}