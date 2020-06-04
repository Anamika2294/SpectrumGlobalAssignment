package com.example.spectrumglobalassignment

import android.app.admin.DevicePolicyManager
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


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {


    var dataList : MutableList<RespnseDataItem> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: DataAdapter
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter= DataAdapter(dataList,this)
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

        recyclerView.adapter= DataAdapter(newDataList,this)
        recyclerView.adapter?.notifyDataSetChanged()

        return true

    }

}

