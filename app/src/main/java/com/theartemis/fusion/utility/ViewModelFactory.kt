package com.theartemis.fusion.utility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.theartemis.fusion.data.DataRepository
import com.theartemis.fusion.ui.fragments.addpost.AddPostFragment
import com.theartemis.fusion.ui.fragments.addpost.AddPostViewModel
import com.theartemis.fusion.ui.fragments.home.HomeViewModel
import com.theartemis.fusion.ui.signin.SignInViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> SignInViewModel(
                dataRepository
            ) as T

            modelClass.isAssignableFrom(AddPostViewModel::class.java) -> AddPostViewModel(
                dataRepository
            ) as T

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                dataRepository
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel: " + modelClass.name)
        }

    }
}