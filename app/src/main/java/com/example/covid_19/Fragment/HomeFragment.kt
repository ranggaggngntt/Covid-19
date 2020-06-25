package com.example.covid_19.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid_19.Activity.GejalaActivity
import com.example.covid_19.Activity.KnowMoreActivity
import com.example.covid_19.Activity.PencegahanActivity
import com.example.covid_19.Adapter.GejalaAdapter
import com.example.covid_19.Adapter.PencegahanAdapter
import com.example.covid_19.model.GejalaModel
import com.example.covid_19.R
import com.example.covid_19.model.pencegahanModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment() {

    lateinit var gejalaList: ArrayList<GejalaModel>
    lateinit var pencegahanList: ArrayList<pencegahanModel>

    private fun gejala() {
        gejalaList = ArrayList()
        gejalaList.add(
            GejalaModel(
                R.drawable.ic_crough,
                "Batuk"
            )
        )
        gejalaList.add(
            GejalaModel(
                R.drawable.ic_flu,
                "Pilek"
            )
        )
        gejalaList.add(
            GejalaModel(
                R.drawable.ic_throat,
                "Sakit Tenggorokan"
            )
        )
        gejalaList.add(
            GejalaModel(
                R.drawable.ic_fever,
                "Demam"
            )
        )
    }

    private fun pencegahan() {
        pencegahanList = ArrayList()
        pencegahanList.add(
            pencegahanModel(
                R.drawable.wear_mask,
                "Memakai Masker"
            )
        )
        pencegahanList.add(
            pencegahanModel(
                R.drawable.ic_washing_hands,
                "Cuci Tangan"
            )
        )
        pencegahanList.add(
            pencegahanModel(
                R.drawable.ic_social_distancing,
                "Jaga Jarak"
            )
        )
        pencegahanList.add(
            pencegahanModel(
                R.drawable.ic_stay_home,
                "Tetap Dirumah"
            )
        )
    }

    private fun showGejala() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter =
            GejalaAdapter(context!!, gejalaList)
    }

    private fun showPencegahan() {
        recyclerViewPencegah.layoutManager = LinearLayoutManager(context)
        recyclerViewPencegah.adapter = PencegahanAdapter(context!!, pencegahanList)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_home
            ,container,false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        gejala()
        pencegahan()
        showGejala()
        showPencegahan()
        getGlobalData()

        txtViewGejala.setOnClickListener{
            var i = Intent(context, GejalaActivity::class.java)
            startActivity(i)
        }
        txtViewPencegah.setOnClickListener{
            var a = Intent(context, PencegahanActivity::class.java)
            startActivity(a)
        }
        btnKnowMore.setOnClickListener {
            var intent = Intent(context, KnowMoreActivity::class.java)
            startActivity(intent)
        }
    }

    fun getGlobalData(){
        val url:String = "https://corona.lmao.ninja/v2/all"

        val stringRequest = StringRequest(Request.Method.GET,
        url,
        Response.Listener <String> {
           var jsonObject = JSONObject(it.toString())

            globalPositif.text = jsonObject.getString("cases")
            globalSembuh.text = jsonObject.getString("recovered")
            globalMeninggal.text = jsonObject.getString("deaths")
        },
        Response.ErrorListener {
            Toast.makeText(context, "Something Wrong",Toast.LENGTH_SHORT)
            globalPositif.text = "-"
            globalSembuh.text = "-"
            globalMeninggal.text = "-"
        }
        )

        var requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }

}
