package com.example.spectrumglobalassignment.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spectrumglobalassignment.MainActivity
import com.example.spectrumglobalassignment.MembersActivity
import com.example.spectrumglobalassignment.Model.RespnseDataItem
import com.example.spectrumglobalassignment.R
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import java.io.Serializable



class DataAdapter(private var dataList: List<RespnseDataItem>, private val context: Context) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel=dataList.get(position)
        Picasso.get().load(dataModel.logo).into(holder.company_logo)



        holder.company_name.text=dataModel.company;
        holder.company_website.text=dataModel.website;
        holder.company_description.text=dataModel.about;

        holder.btn_favourite.setIconResource(
            if ((context as MainActivity).readstate(dataModel._id))
                R.drawable.star_checked
            else
                R.drawable.star_unchecked
        )

        if ((context as MainActivity).readFollowStaed(dataModel._id)){
            holder.btn_follow.text= "Followed"
        }
        else{
            holder.btn_follow.text= "follow"
        }



        holder?.itemView?.setOnClickListener {
            val intent = Intent(holder.itemView.context, MembersActivity::class.java)

            intent.putExtra("MEMBER_LIST", dataModel.members as Serializable)
            context.startActivity(intent)

        }

        holder.btn_favourite.setOnClickListener{
            if(!dataModel.isFavourite){
                dataModel.isFavourite= true;
                holder.btn_favourite.setIconResource(R.drawable.star_checked)
                (context as MainActivity).savestate(dataModel._id,true)


            }
            else{
                dataModel.isFavourite= false;
                holder.btn_favourite.setIconResource(R.drawable.star_unchecked)
                (context as MainActivity).savestate(dataModel._id,false)

            }
        }

        holder.btn_follow.setOnClickListener{
            if(!dataModel.isFollwed){
                dataModel.isFollwed= true;
                holder.btn_follow.text= "Followed"
                (context as MainActivity).saveFollowStaed(dataModel._id,true)

            }
            else{
                dataModel.isFollwed= false;
                holder.btn_follow.text= "follow"
                (context as MainActivity).saveFollowStaed(dataModel._id,false)
            }
        }



    }


    inner class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
         var company_name:TextView
         var company_website: TextView
         var company_description: TextView
         var company_logo: ImageView
         var btn_favourite: MaterialButton
         var btn_follow: MaterialButton



        init {
            company_name=itemLayoutView.findViewById(R.id.tv_company_name)
            company_website=itemLayoutView.findViewById(R.id.tv_company_website)
            company_description=itemLayoutView.findViewById(R.id.tv_company_description)
            company_logo=itemLayoutView.findViewById(R.id.img_company_logo)
            btn_favourite=itemLayoutView.findViewById<MaterialButton>(R.id.btn_fav)
            btn_follow=itemLayoutView.findViewById(R.id.btn_follow)

        }

    }

}
