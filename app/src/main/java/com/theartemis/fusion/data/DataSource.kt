package com.theartemis.fusion.data

import android.net.Uri
import androidx.lifecycle.LiveData

interface DataSource {

    fun signInGoogle(token:String): LiveData<User>
    fun addPost(spinner: String, title: String, description: String,imageUri: Uri): LiveData<Post>
    fun fetchPost(): LiveData<List<Post>>

}