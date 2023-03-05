package com.example.newskotlinapplication.ui.news_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newskotlinapplication.R
import com.example.newskotlinapplication.api.model.newsResponse.NewsItem
import com.example.newskotlinapplication.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsDetailsBinding
    private lateinit var news: NewsItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    news = ((intent.getSerializableExtra("news") as? NewsItem ))!!
        binding.news=news
    }
}