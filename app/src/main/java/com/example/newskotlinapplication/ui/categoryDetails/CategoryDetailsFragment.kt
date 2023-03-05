package com.example.newskotlinapplication.ui.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newskotlinapplication.R
import com.example.newskotlinapplication.api.model.sourceResponse.SourceItem
import com.example.newskotlinapplication.databinding.FragmentDetailsCategoryBinding
import com.example.newskotlinapplication.ui.categories.Category
import com.example.newskotlinapplication.ui.news.NewsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class CategoryDetailsFragment :Fragment() {
    lateinit var viewBinding : FragmentDetailsCategoryBinding
    lateinit var category: Category
    lateinit var viewModel: CategoriesDetailsViewModel

    companion object{
        fun getInstance(categoryy: Category):CategoryDetailsFragment{
            val fragment = CategoryDetailsFragment()
             fragment.category=categoryy
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDetailsCategoryBinding.inflate(inflater, container,false)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =ViewModelProvider(this).get(CategoriesDetailsViewModel::class.java)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // loadNewsSources()
        viewModel.loadNewsSources(category.idApi)
       subscribeToLiveData()

        viewBinding.tryAgain.setOnClickListener{
           // loadNewsSources()
            viewModel.loadNewsSources(category.idApi)
            subscribeToLiveData()
        }
    }

    fun subscribeToLiveData(){
        viewModel.sourcesLiveData.observe(viewLifecycleOwner, object :Observer<List<SourceItem?>?>{
            override fun onChanged(t: List<SourceItem?>?) {
                bindingSourcesInTabLayout(t)
            } })
        viewModel.showLoadingLayout.observe(viewLifecycleOwner, object :Observer<Boolean>{
            override fun onChanged(show: Boolean?) {
                if(show==true)
                    showLoadingLayout()
                else
                    hideLoadingLayout()
            } })
        viewModel.showErrorLayout.observe(viewLifecycleOwner, object :Observer<String>{
            override fun onChanged(t: String?) {
                showErrorMessage(t)
            }

        })
    }

    private fun changeNewsFragment(source: SourceItem){
        viewModel.loadNewsSources(category.idApi)
        subscribeToLiveData()
        //السطرين دول من عندي
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_n, NewsFragment.getInstance(source))
            .commit()
    }


    private fun showErrorMessage(message: String?) {
        with(viewBinding) {
           // loddingIndector.isVisible=false
            errorLayout.isVisible=true
            errorMessage.text = message
        }
    }

    private fun showLoadingLayout() {
        with(viewBinding) {
            //loddingIndector.isVisible=true
            errorLayout.isVisible=false
        }
    }


    private fun hideLoadingLayout() {
        with(viewBinding) {
            //loddingIndector.isVisible=true
            errorLayout.isVisible=false
        }
    }


    fun bindingSourcesInTabLayout(sourcesList: List<SourceItem?>?){
        sourcesList?.forEach { source->
               val tab= viewBinding.tabLayout.newTab()
            tab.text= source?.name
            tab.tag= source
            viewBinding.tabLayout.addTab(tab)
            (tab.view.layoutParams as LinearLayout.LayoutParams).marginStart =12
            (tab.view.layoutParams as LinearLayout.LayoutParams).marginEnd =12

//            val layoutsPrams = LinearLayout.LayoutParams(tab.view.layoutParams)
//            layoutsPrams.marginStart=12
//            layoutsPrams.marginEnd=12
//
//           tab.view.layoutParams=layoutsPrams

        }
        viewBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source=  tab?.tag as SourceItem
                changeNewsFragment(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source=  tab?.tag as SourceItem
                changeNewsFragment(source)            }

        })

        //viewBinding.tabLayout.getTabAt(0)?.select()
    }
}

