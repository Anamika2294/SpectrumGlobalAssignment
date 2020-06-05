package com.example.spectrumglobalassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letsgetcheckedassignment.ApiClient
import com.example.spectrumglobalassignment.Model.RespnseDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.view.MenuItemCompat

import android.view.Menu
import androidx.appcompat.widget.SearchView

import androidx.appcompat.widget.Toolbar
import android.app.Dialog
import android.widget.RadioButton
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import com.example.spectrumglobalassignment.Adapters.DataAdapter
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,View.OnClickListener {



    var dataList : MutableList<RespnseDataItem> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var btnSort: Button
    lateinit var adapter: DataAdapter
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSort = findViewById(R.id.btn_sort)
        btnSort.setOnClickListener(this)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter=
            DataAdapter(dataList, this)
        recyclerView.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        getData()

    }
    private fun getData() {

        val call: Call<List<RespnseDataItem>> = ApiClient.getClient.getData()
        call.enqueue(object : Callback<List<RespnseDataItem>> {

            override fun onResponse(call: Call<List<RespnseDataItem>>?, response: Response<List<RespnseDataItem>>?) {
                //progerssProgressDialog.dismiss()
                Log.v("Data",""+response!!.body()!!.get(0)._id);
                dataList.addAll(response!!.body()!!)
                recyclerView.adapter?.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<RespnseDataItem>>?, t: Throwable?) {
                // progerssProgressDialog.dismiss()
            }

        })
    }

    fun showDialogBox(){

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_company_sort_dialog_box)
        dialog.setTitle("This is my custom dialog box")
        dialog.setCancelable(true)
        // there are a lot of settings, for dialog, check them all out!
        // set up radiobutton
        val rg = dialog.findViewById(R.id.radio_sort) as RadioGroup
        val rd1 = dialog.findViewById(R.id.radio_name_asc) as RadioButton
        val rd2 = dialog.findViewById(R.id.radio_name_desc) as RadioButton
        val btn_ok = dialog.findViewById(R.id.btn_okay) as Button

        btn_ok.setOnClickListener{
            var id: Int = rg.checkedRadioButtonId
            when(id){
                R.id.radio_name_asc ->{
                    recyclerView.adapter= DataAdapter(
                        dataList.sortedBy { it.company },
                        this
                    )
                    recyclerView.adapter?.notifyDataSetChanged()
                    }
                R.id.radio_name_desc ->
                    {dataList.sortedWith(compareByDescending { it.company })
                        recyclerView.adapter=
                            DataAdapter(
                                dataList.sortedByDescending { it.company },
                                this
                            )
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
            }
            dialog.cancel()
        }

        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menuitems, menu)
        val menuItem = menu.findItem(R.id.actionsearch)
        val searchView = MenuItemCompat.getActionView(menuItem) as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
       return false;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val newText=newText!!.toLowerCase();
        var newDataList : MutableList<RespnseDataItem> = ArrayList()

        for(responseItem in dataList){
           val companyName=responseItem.company.toLowerCase()
            if(companyName.contains(newText)){
                newDataList.add(responseItem)
            }
        }

        recyclerView.adapter=
            DataAdapter(newDataList, this)
        recyclerView.adapter?.notifyDataSetChanged()

        return true

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_sort -> {
              showDialogBox()
            }
            else -> {
                // else condition
            }
        }
    }

}


