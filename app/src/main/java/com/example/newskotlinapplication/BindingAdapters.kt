package com.example.newskotlinapplication

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageURL")
fun loadImageFromUrl(imageView:ImageView, url:String){
   Glide.with(imageView)
       .load(url)
       .placeholder(R.drawable.ic_image)
       .into(imageView)
}

@BindingAdapter("card_color")
fun changeCardBackground(cardView: CardView, colorId:Int){
    cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,colorId))
}

@BindingAdapter("image")
fun setImageWithId(imageView: ImageView, imageId:Int){
 imageView.setImageResource(imageId)
}