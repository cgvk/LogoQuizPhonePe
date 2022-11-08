package com.logoquiz.phonepe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logoquiz.phonepe.R
import com.logoquiz.phonepe.common.isGreaterThan
import com.logoquiz.phonepe.databinding.ItemButtonBinding

/**
 * Created by Vinod Kumar on 08/11/22.
 */

class SuggestionAdapter(private val suggestSource:MutableList<String>,private val answer:String,val listener:AdapterCallBack) : RecyclerView.Adapter<SuggestionAdapter.ViewHolder?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(suggestSource[holder.adapterPosition])
        holder.itemView.setOnClickListener {
            if(answer.capitalize().contains(suggestSource[holder.adapterPosition])){
                listener.onClickOfButton(suggestSource[holder.adapterPosition])
            }
        }
    }


    override fun getItemCount(): Int {
        return suggestSource.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bind: ItemButtonBinding

        init {
            bind = ItemButtonBinding.bind(itemView)
        }

        fun onBind(text:String){
            bind.tvSuggestion.text = text.capitalize()
        }
    }

    interface AdapterCallBack {
        fun onClickOfButton(suggestion:String)
    }
}