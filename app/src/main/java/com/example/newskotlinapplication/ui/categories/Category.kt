package com.example.newskotlinapplication.ui.categories

import com.example.newskotlinapplication.R

data class Category(val id:String , val name:String, val imageId: Int, val backgroundColorID:Int){
    companion object{
       fun getCategoriesList():List<Category>{

           return listOf(
               Category("sports","Sports", imageId = R.drawable.ball, backgroundColorID = R.color.red),
               Category("general","General", imageId = R.drawable.politics, backgroundColorID = R.color.blue),
               Category("health","Health", imageId = R.drawable.health, backgroundColorID = R.color.pink),
               Category("business","Business", imageId = R.drawable.bussines, backgroundColorID = R.color.brown),
               Category("technology","Technology", imageId = R.drawable.environment, backgroundColorID = R.color.babyBlue),
               Category("science","Science", imageId = R.drawable.science, backgroundColorID = R.color.yellow),

           )
       }
    }
}

