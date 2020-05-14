package com.example.covid_19.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.Adapter.SearchAdapter
import com.example.covid_19.Api.ApiService
import com.example.covid_19.R
import com.example.covid_19.model.Attributes
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var searchList: List<Attributes>


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
        ,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

        var apicall = ApiService.create().getData()

        apicall.enqueue(object : retrofit2.Callback<List<Attributes>> {


            override fun onFailure(call: Call<List<Attributes>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<Attributes>>,
                response: Response<List<Attributes>>
            ) {
                if (response.isSuccessful) {
                    val searchList = response.body()!!

                    var Adapter = SearchAdapter(searchList)

                    val layoutManager = LinearLayoutManager(context)

                    recyclerViewSearch.layoutManager = layoutManager
                    recyclerViewSearch.adapter = Adapter
                }
            }
        } )
    }
}
