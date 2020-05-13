package com.example.covid_19

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject
import com.example.covid_19.HomeFragment as HomeFragment1

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
        getGlobalData()
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
