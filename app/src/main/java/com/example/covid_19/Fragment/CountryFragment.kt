package com.example.covid_19.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.Adapter.CountryAdapter
import com.example.covid_19.KawalCoronaApi.KawalCoronaApi
import com.example.covid_19.KawalCoronaApi.apiRequest
import com.example.covid_19.KawalCoronaApi.httpClient
import com.example.covid_19.R
import com.example.covid_19.model.kawalcoronaCountryItem
//import com.example.covid_19.viewmodel.CountryViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.country_item.*
import kotlinx.android.synthetic.main.fragment_country.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import java.util.Observer

class CountryFragment : Fragment() {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    var datacountry: MutableList<kawalcoronaCountryItem> = ArrayList()
//    private val viewModel by viewModels<CountryViewModel>()
    private var adapter: CountryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_country
            , container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {
        getCountryData()

        }

    private fun getCountryData() {

        val httpClient = httpClient()
        val apiRequest = apiRequest<KawalCoronaApi>(httpClient)

        val call = apiRequest.getCountry()

        call.enqueue(object : Callback<List<kawalcoronaCountryItem>>{
            override fun onFailure(call: Call<List<kawalcoronaCountryItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<kawalcoronaCountryItem>>,
                response: Response<List<kawalcoronaCountryItem>>
            ) {
                when {
                    response.isSuccessful -> when {
                        response.body()?.size != 0 ->showList(response.body()!!)
                        else -> {
                            Toast.makeText(context, "Gagal Ambil Data", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else -> {
                        Toast.makeText(context, "Gagal Ambil Data", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

    private fun showList(getDetailItem: List<kawalcoronaCountryItem>){
        recyclerViewCountry.layoutManager = LinearLayoutManager(context)
        recyclerViewCountry.adapter = CountryAdapter(requireContext(), getDetailItem) {
            val countryList = it
            val httpClient = httpClient()
            val apirequest = apiRequest<KawalCoronaApi>(httpClient)
            val call = apirequest.getCountry()

            call.enqueue(object : Callback<List<kawalcoronaCountryItem>>{
                override fun onFailure(call: Call<List<kawalcoronaCountryItem>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<kawalcoronaCountryItem>>,
                    response: Response<List<kawalcoronaCountryItem>>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body() as kawalcoronaCountryItem
                        println(nameCountry)
                    } else {
                        Toast.makeText(
                            context,
                            "Tidak Berhasil Parsing Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
        }
    }
}
