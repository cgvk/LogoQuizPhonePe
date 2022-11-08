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

class ResultAdapter(private val size :Int) : RecyclerView.Adapter<ResultAdapter.ViewHolder?>() {

    var userSubmitted : MutableList<String>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!userSubmitted.isNullOrEmpty() && userSubmitted?.size?.minus(1).isGreaterThan(position) == true)
            userSubmitted?.get(position)?.let { holder.onBind(it) }

    }


    override fun getItemCount(): Int {
        return size
    }

    fun setData(text:String) {
        userSubmitted?.add(text)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bind: ItemButtonBinding

        init {
            bind = ItemButtonBinding.bind(itemView)
        }

        fun onBind(answer:String) {
            bind.tvSuggestion.text = answer
        }

    }
}