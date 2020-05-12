package com.example.covid_19

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_my_friend.*

class MyFriendFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.HORIZONTAL, true)
        val gejalaList = ArrayList<GejalaModel>()
        gejalaList.add(GejalaModel("Batuk", "Batuk adalah bla bla bla bla"))
        gejalaList.add(GejalaModel("Pilek", "Batuk adalah bla bla bla bla"))
        gejalaList.add(GejalaModel("Sakit Tenggorokan", "Batuk adalah bla bla bla bla"))
        gejalaList.add(GejalaModel("Demam", "Batuk adalah bla bla bla bla"))

        val gejalaAdapter = GejalaAdapter(gejalaList)

        recyclerView.adapter = gejalaAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_friend
            ,container,false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}
