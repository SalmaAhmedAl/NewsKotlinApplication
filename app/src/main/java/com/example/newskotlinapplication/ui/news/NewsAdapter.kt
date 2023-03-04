package com.example.newskotlinapplication.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlinapplication.api.model.newsResponse.NewsItem
import com.example.newskotlinapplication.databinding.ItemNewsBinding

class NewsAdapter (var items: List<NewsItem?>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>()  {
    class ViewHolder (val viewBinding : ItemNewsBinding) :RecyclerView.ViewHolder(viewBinding.root){
       fun bind(news: NewsItem?){
           viewBinding.news=news
           viewBinding.invalidateAll()
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item)

    }

    override fun getItemCount(): Int =items?.size?:0
    fun changeData(articles: List<NewsItem?>?) {
       items=articles
        notifyDataSetChanged()

    }


}