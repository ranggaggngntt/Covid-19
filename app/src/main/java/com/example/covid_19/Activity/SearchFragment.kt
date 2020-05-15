package com.example.covid_19.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.Adapter.SearchAdapter
import com.example.covid_19.Api.Api
import com.example.covid_19.Api.apiRequest
import com.example.covid_19.Api.httpClient
import com.example.covid_19.R
import com.example.covid_19.model.Attributes
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_search
            , container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

        val httpClient = httpClient()
        val apiRequest = apiRequest<Api>(httpClient)

        val call = apiRequest.getData()
        call.enqueue(object : Callback<List<Attributes>> {
            override fun onFailure(call: Call<List<Attributes>>, t: Throwable) {
                Toast.makeText(context, "Koneksi Gagal", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<Attributes>>,
                response: Response<List<Attributes>>
            ) {
                when {
                    response.isSuccessful -> when {
                        response.body()?.size != 0 -> showList(response.body()!!)
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


    private fun showList(detailData: List<Attributes>) {
        recyclerViewSearch.layoutManager = LinearLayoutManager(context)
        recyclerViewSearch.adapter = SearchAdapter(context!!, detailData) {
            val datalist = it
            val httpClient = httpClient()
            val apiRequest = apiRequest<Api>(httpClient)
            val call = apiRequest.getDetailData(datalist.FID)
            call.enqueue(object : Callback<Attributes> {
                override fun onFailure(call: Call<Attributes>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<Attributes>,
                    response: Response<Attributes>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body() as Attributes
                        println(data.provinsi)
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