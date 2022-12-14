package com.logoquiz.phonepe.common

import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.logoquiz.phonepe.R

/**
 * Created by Vinod Kumar on 08/11/22.
 */

fun ImageView.loadUrl(url:String){
    Glide.with(context).load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .centerCrop()
        .into(this)
}
fun ImageView.loadUrl(res:Int){
    Glide.with(context).load(res)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(this)
}
fun Button.clear(){
       this.setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent))
}
fun Button.incorrectAnswer(buttonsArray:Array<Button>,answer:Int){
    this.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
    buttonsArray[answer-1].setBackgroundColor(ContextCompat.getColor(context, R.color.green))

}

infix fun <T : Comparable<T>> T?.isGreaterThan(other: T?): Boolean? =
    if (this != null && other != null) this >= other else null

