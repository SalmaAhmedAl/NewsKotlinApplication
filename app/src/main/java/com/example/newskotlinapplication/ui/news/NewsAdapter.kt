package com.example.newskotlinapplication.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newskotlinapplication.R
import com.example.newskotlinapplication.api.model.newsResponse.NewsItem
import com.example.newskotlinapplication.databinding.ItemNewsBinding

class NewsAdapter (var items: List<NewsItem?>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>()  {
    class ViewHolder (val viewBinding : ItemNewsBinding) :RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        with(holder.viewBinding) {
            author.text=item?.author
            title.text= item?.title
            time.text=item?.publishedAt

            Glide.with(holder.itemView)
                .load(item?.urlToImage).placeholder(R.drawable.ic_image)
                .into(holder.viewBinding.imageNews)

        }
    }

    override fun getItemCount(): Int =items?.size?:0
    fun changeData(articles: List<NewsItem?>?) {
      items=articles
        notifyDataSetChanged()
    }
}