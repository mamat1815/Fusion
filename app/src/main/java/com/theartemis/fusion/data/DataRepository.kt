package com.theartemis.fusion.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theartemis.fusion.data.remote.RemoteDataSource

class DataRepository(private val repository: RemoteDataSource) :DataSource{

    val signIn = MutableLiveData<User>()
    val addPost = MutableLiveData<Post>()
    val fetchPost = MutableLiveData<List<Post>>()
    val fetchDetailPost = MutableLiveData<Post>()
    val onLoading = MutableLiveData<Boolean>()
    val onFetching = MutableLiveData<Boolean>()

    override fun signInGoogle(token: String): LiveData<User> {
        repository.signInWithGoogle(token, object : RemoteDataSource.SignInWithGoogleCallback{
            override fun onResponse(response: User) {
                signIn.value = response
            }
        })
        return signIn
    }

    override fun addPost(
        spinner: String,
        title: String,
        description: String,
        imageUri: Uri
    ): LiveData<Post> {
        repository.addPost(spinner,title,description,imageUri,object : RemoteDataSource.AddPostCallback{
            override fun onResponse(response: Post) {
                addPost.value = response
            }

        })
        return addPost
    }

    override fun fetchPost(): LiveData<List<Post>> {
        repository.fetchPost(object : RemoteDataSource.FetchPostCallback {
            override fun onResponse(response: List<Post>) {
                fetchPost.value = response
            }

        })
        return fetchPost
    }

    override fun fetchDetailPost(id: String): LiveData<Post> {
        repository.fetchDetailPost(id, object : RemoteDataSource.DetailPostCallback {
            override fun onResponse(response: Post) {
                fetchDetailPost.value = response
            }

        })
        return fetchDetailPost
    }


}