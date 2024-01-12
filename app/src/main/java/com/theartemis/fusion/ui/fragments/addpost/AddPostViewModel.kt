package com.theartemis.fusion.ui.fragments.addpost

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.theartemis.fusion.data.DataRepository

class AddPostViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun addPost(spinner: String, title: String, description: String,imageUri: Uri)
    = dataRepository.addPost(spinner, title,description,imageUri)

}