package com.example.spectrumglobalassignment

import android.app.Dialog
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

class MembersActivity : AppCompatActivity(), SearchView.OnQueryTextListener, View.OnClickListener {


    lateinit var recyclerView: RecyclerView
    lateinit var btnSort: Button
    lateinit var adapter: DataAdapter
    lateinit var toolbar: Toolbar
    var members: List<Member> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)
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

//    fun showDialogBox(){
//
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.custom_company_sort_dialog_box)
//        dialog.setTitle("This is my custom dialog box")
//        dialog.setCancelable(true)
//        // there are a lot of settings, for dialog, check them all out!
//        // set up radiobutton
//        val rg = dialog.findViewById(R.id.radio_sort) as RadioGroup
//        val rd1 = dialog.findViewById(R.id.radio_name_asc) as RadioButton
//        val rd2 = dialog.findViewById(R.id.radio_name_desc) as RadioButton
//        val btn_ok = dialog.findViewById(R.id.btn_okay) as Button
//
//        btn_ok.setOnClickListener{
//            var id: Int = rg.checkedRadioButtonId
//            when(id){
//                R.id.radio_name_asc ->{
//                    recyclerView.adapter= DataAdapter(
//                        dataList.sortedBy { it.company },
//                        this
//                    )
//                    recyclerView.adapter?.notifyDataSetChanged()
//                }
//                R.id.radio_name_desc ->
//                {dataList.sortedWith(compareByDescending { it.company })
//                    recyclerView.adapter= DataAdapter(
//                        dataList.sortedByDescending { it.company },
//                        this
//                    )
//                    recyclerView.adapter?.notifyDataSetChanged()
//                }
//            }
//            dialog.cancel()
//        }
//
//        dialog.show()
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menuitems, menu)
        val menuItem = menu.findItem(R.id.actionsearch)
        val searchView = MenuItemCompat.getActionView(menuItem) as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       return true;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       return false;
    }

    override fun onClick(v: View?) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}