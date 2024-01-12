package com.theartemis.fusion.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.theartemis.fusion.data.DataRepository
import com.theartemis.fusion.data.User
import kotlinx.coroutines.launch

class SignInViewModel(private val dataRepository: DataRepository): ViewModel() {

  /*  private val _signInResult = MutableLiveData<Response<User>>()
    val signInResult: LiveData<Response<User>> get() = _signInResult

    fun signInWithGoogle(token:String) {


        viewModelScope.launch {
            _signInResult.value = dataRepository.signInWithGoogle(token)
        }
    }*/

    fun signInWithGoogle(token:String) = dataRepository.signInGoogle(token)



}