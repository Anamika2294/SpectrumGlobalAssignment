package com.example.spectrumglobalassignment.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spectrumglobalassignment.MembersActivity
import com.example.spectrumglobalassignment.Model.Member
import com.example.spectrumglobalassignment.Model.RespnseDataItem
import com.example.spectrumglobalassignment.R
import com.squareup.picasso.Picasso
import java.io.Serializable

class MemberAdapter(private var dataList: List<Member>, private val context: Context) : RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    // var onItemClick: ((DataModel) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.member_item, parent, false))
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel=dataList.get(position)
        holder.member_name.text=dataModel.name.first+" "+dataModel.name.last ;
        holder.member_age.text= dataModel.age.toString();
        holder.member_phone.text=dataModel.phone;

    }


    inner class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var member_name: TextView
        lateinit var member_age: TextView
        lateinit var member_phone: TextView
        lateinit var member_email: TextView



        init {
            member_name=itemLayoutView.findViewById(R.id.tv_member_name)
            member_age=itemLayoutView.findViewById(R.id.tv_member_age)
            member_phone=itemLayoutView.findViewById(R.id.tv_member_phone)
            member_email=itemLayoutView.findViewById(R.id.tv_member_mail)

        }

    }

}
