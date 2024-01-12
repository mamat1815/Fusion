package com.theartemis.fusion.ui.fragments.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.theartemis.fusion.data.DataRepository
import com.theartemis.fusion.data.Post

class HomeViewModel(private val dataRepository: DataRepository):ViewModel() {

    fun fetchPost() : LiveData<List<Post>> = dataRepository.fetchPost()

}