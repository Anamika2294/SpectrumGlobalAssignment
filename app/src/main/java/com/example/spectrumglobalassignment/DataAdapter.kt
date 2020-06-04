package com.example.spectrumglobalassignment

import android.content.Context
import android.graphics.Color
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spectrumglobalassignment.Model.RespnseDataItem
import com.squareup.picasso.Picasso

class DataAdapter(private var dataList: List<RespnseDataItem>, private val context: Context) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

   // var onItemClick: ((DataModel) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel=dataList.get(position)
        Picasso.get().load(dataModel.logo).into(holder.company_logo)



        holder.company_name.text=dataModel.company;
        holder.company_website.text=dataModel.website;
        holder.company_description.text=dataModel.about;

    }


    inner class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var company_name:TextView
        lateinit var company_website: TextView
        lateinit var company_description: TextView
        lateinit var company_logo: ImageView



        init {
            company_name=itemLayoutView.findViewById(R.id.tv_company_name)
            company_website=itemLayoutView.findViewById(R.id.tv_company_website)
            company_description=itemLayoutView.findViewById(R.id.tv_company_description)
            company_logo=itemLayoutView.findViewById(R.id.img_company_logo)



//            itemLayoutView.setOnClickListener {
//                onItemClick?.invoke(dataList[adapterPosition])
//            }

        }

    }

}
