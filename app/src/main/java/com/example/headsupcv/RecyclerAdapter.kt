package com.example.headsupcv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.celeb_item.view.*

class RecyclerAdapter (var Celebrities :ArrayList<List<String>>): RecyclerView.Adapter<RecyclerAdapter.itemViewHolder>() {
    class itemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.celeb_item,
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val name=Celebrities[position][0]
        val Taboo1=Celebrities[position][1]
        val Taboo2=Celebrities[position][2]
        val Taboo3=Celebrities[position][3]

        holder.itemView.apply {
            tvName.text=name
            tvT1.text=Taboo1
            tvT2.text=Taboo2
            tvT3.text=Taboo3
        }
    }

    override fun getItemCount(): Int = Celebrities.size

}