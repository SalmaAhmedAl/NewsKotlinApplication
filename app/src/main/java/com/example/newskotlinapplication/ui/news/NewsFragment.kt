package com.example.newskotlinapplication.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlinapplication.api.model.newsResponse.NewsItem
import com.example.newskotlinapplication.api.model.sourceResponse.SourceItem
import com.example.newskotlinapplication.databinding.FragmentNewsBinding
import com.example.newskotlinapplication.ui.news_details.NewsDetailsActivity

class NewsFragment:Fragment() {
    companion object{
        fun getInstance(source: SourceItem):NewsFragment{
             val newFragment= NewsFragment()
            newFragment.source = source
            return newFragment
        }
    }
    lateinit var source : SourceItem
    lateinit var viewBinding : FragmentNewsBinding
    lateinit var viewModel: NewsViewModel

    var PAGE_SIZE =20
    var currentPage=1
    var isLoaded= false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerNews()
        getNew()

        subscribeToLiveData()
    }

    private fun getNew() {
        viewModel.getNew(source.id?:"", pageSize = PAGE_SIZE, page = currentPage)
        isLoaded=false
    }

    fun subscribeToLiveData(){
        viewModel.newsList.observe(viewLifecycleOwner) {
            bindNewsList(it)
        }
        viewModel.showError.observe(viewLifecycleOwner) {
            showErrorMessage(it)
        }

        viewModel.showLoading.observe(viewLifecycleOwner){show->
            if(show)
                showLoadingLayout()
            else
                hideLoadingLayout()

        }
    }

    private fun hideLoadingLayout() {
        with(viewBinding) {
            loddingIndector.isVisible=false
        }
    }

    val newsAdapter =NewsAdapter(null)
    private fun initRecyclerNews() {
        viewBinding.recyclerNews.adapter=newsAdapter
        viewBinding.recyclerNews.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition =layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                val visibleThreshold = 10
                if(!isLoaded && totalItemCount - lastVisibleItemPosition <=visibleThreshold ){
                    isLoaded =true
                    currentPage++
                    getNew()
                }
            }
        })

        newsAdapter.onNewsClick = object :NewsAdapter.OnNewsClick{
            override fun onItemClick(news: NewsItem?) {
                val intent = Intent(requireContext(), NewsDetailsActivity::class.java)
                  intent.putExtra("news", news)
                startActivity(intent)
            }
        }
    }



    private fun bindNewsList(articles: List<NewsItem?>?) {
     //show news in Recycler view
        with(viewBinding) {
            loddingIndector.isVisible=false
            errorLayout.isVisible=false
        }
        newsAdapter.changeData(articles)
    }

    private fun showErrorMessage(message: String?) {
        with(viewBinding) {
            loddingIndector.isVisible=false
            errorLayout.isVisible=true
            errorMessage.text = message
        }
    }

    private fun showLoadingLayout() {
        with(viewBinding) {
            loddingIndector.isVisible=true
            errorLayout.isVisible=false
        }
    }
}