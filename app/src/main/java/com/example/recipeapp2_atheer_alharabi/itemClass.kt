package com.example.recipeapp2_atheer_alharabi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items.view.*

class itemClass(val context: Context, val input:ArrayList<dataRec>) : RecyclerView.Adapter<itemClass.itemViewHolder>() {

        class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


        override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
            //take ont data and add it to textview up
            val message = input[position]

            holder.itemView.apply {

                tv2.text = message.title+"\n"+message.author+"\n"+ message.ingre+"\n"+message.instruc


            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
            return itemViewHolder(

                LayoutInflater.from(context).inflate(
                    R.layout.items,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int = input.size
}