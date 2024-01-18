package com.theartemis.fusion.ui.detailpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.theartemis.fusion.R
import com.theartemis.fusion.data.DataRepository
import com.theartemis.fusion.data.remote.RemoteDataSource
import com.theartemis.fusion.databinding.ActivityDetailPostBinding
import com.theartemis.fusion.ui.signin.SignInViewModel
import com.theartemis.fusion.utility.Utility.loadImage
import com.theartemis.fusion.utility.ViewModelFactory

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding
    private lateinit var viewModel: DetailPostActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataRepository = DataRepository(RemoteDataSource())
        val viewModelFactory = ViewModelFactory(dataRepository)
        viewModel = ViewModelProvider(this,viewModelFactory)[DetailPostActivityViewModel::class.java]


        val id = intent.getStringExtra(EXTRA_ID)
        fetchData(id!!)
        Log.d("DataBaseDetail",id)

    }

    private fun fetchData(id: String) {
        viewModel.fetchDetailData(id).observe(this@DetailPostActivity){data ->
            binding.apply {
                tvDetailAuthor.text = data.username
                tvDetailTitle.text = data.title
                tvDetailDescription.text = data.description
                tvDetailSdg.text = data.sdgs

                imgDetailPost.loadImage(data.img)
            }

        }
    }


    companion object {
        const val EXTRA_ID = "extra_id"
    }
}