package com.example.spectrumglobalassignment

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spectrumglobalassignment.Adapters.DataAdapter
import com.example.spectrumglobalassignment.Adapters.MemberAdapter
import com.example.spectrumglobalassignment.Model.Member
import com.example.spectrumglobalassignment.Model.RespnseDataItem
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.provider.MediaStore


class MembersActivity : AppCompatActivity(), SearchView.OnQueryTextListener, View.OnClickListener {


    lateinit var recyclerView: RecyclerView
    lateinit var btnSort: Button
    lateinit var adapter: DataAdapter
    lateinit var toolbar: Toolbar
    var members: List<Member> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSort = findViewById(R.id.btn_sort)
        btnSort.setOnClickListener(this)
        members= intent.getSerializableExtra("MEMBER_LIST") as List<Member>
        Log.v("MemberActivity",""+members);

        btnSort = findViewById(R.id.btn_sort)
        btnSort.setOnClickListener(this)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter=
            MemberAdapter(members, this)
        recyclerView.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

    }

    fun showDialogBox(){

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_member_sort_dialog)
        dialog.setTitle("This is my custom dialog box")
        dialog.setCancelable(true)
        // there are a lot of settings, for dialog, check them all out!
        // set up radiobutton
        val rg = dialog.findViewById(R.id.radio_sort) as RadioGroup
        val rdNameAsc = dialog.findViewById(R.id.radio_name_asc) as RadioButton
        val rdNameDec = dialog.findViewById(R.id.radio_name_desc) as RadioButton
        val rdAgeAsc = dialog.findViewById(R.id.radio_age_asc) as RadioButton
        val rdAgeDec = dialog.findViewById(R.id.radio_age_desc) as RadioButton
        val btn_ok = dialog.findViewById(R.id.btn_okay) as Button

        btn_ok.setOnClickListener{
            var id: Int = rg.checkedRadioButtonId
            when(id){
                R.id.radio_name_asc ->{
                    recyclerView.adapter= MemberAdapter(
                        members.sortedBy { it.name.first },
                        this
                    )
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                R.id.radio_name_desc ->
                {
                    //members.sortedWith(compareByDescending { it.name.first})
                    recyclerView.adapter= MemberAdapter(
                        members.sortedByDescending { it.name.first },
                        this
                    )
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                R.id.radio_age_asc ->
                {
                    //members.sortedWith(compareByDescending { it.name.first})
                    recyclerView.adapter= MemberAdapter(
                        members.sortedBy { it.age },
                        this
                    )
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                R.id.radio_age_desc ->
                {
                    //members.sortedWith(compareByDescending { it.name.first})
                    recyclerView.adapter= MemberAdapter(
                        members.sortedByDescending { it.age },
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
        var newDataList : MutableList<Member> = ArrayList()

        for(responseItem in members){
            val firstName=responseItem.name.first.toLowerCase()
            val lastName=responseItem.name.last.toLowerCase()

            if(firstName.contains(newText) || lastName.contains(newText)){
                Log.v("LastName",""+lastName)
                newDataList.add(responseItem)
            }
        }

        recyclerView.adapter=
            MemberAdapter(newDataList, this)
        recyclerView.adapter?.notifyDataSetChanged()

       return true;
    }

    override fun onClick(v: View?) {
        showDialogBox()
    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter=
            MemberAdapter(members, this)
        recyclerView.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        recyclerView.adapter?.notifyDataSetChanged()



    }

     fun savestate(id:String,isFavourite: Boolean) {
        val aSharedPreferences = this.getSharedPreferences("Favourite", Context.MODE_PRIVATE)
        val aSharedPreferencesEdit = aSharedPreferences.edit()
        aSharedPreferencesEdit.putBoolean(id, isFavourite)
        aSharedPreferencesEdit.apply()
    }


}
