package com.theartemis.fusion.ui.detailpost

import androidx.lifecycle.ViewModel
import com.theartemis.fusion.data.DataRepository

class DetailPostActivityViewModel(private val repository: DataRepository): ViewModel() {
    fun fetchDetailData(id:String) = repository.fetchDetailPost(id)

}