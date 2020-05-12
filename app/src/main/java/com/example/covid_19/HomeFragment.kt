package com.example.covid_19

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var gejalaList: ArrayList<GejalaModel>


    private fun gejala() {
        gejalaList = ArrayList()
        gejalaList.add(GejalaModel("Batuk", "Batuk adalah bla bla bla bla"))
        gejalaList.add(GejalaModel("Pilek", "Batuk adalah bla bla bla bla"))
        gejalaList.add(GejalaModel("Sakit Tenggorokan", "Batuk adalah bla bla bla bla"))
        gejalaList.add(GejalaModel("Demam", "Batuk adalah bla bla bla bla"))
    }

    private fun showGejala() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = GejalaAdapter(context!!, gejalaList)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home
            ,container,false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        gejala()
        showGejala()
    }

}
