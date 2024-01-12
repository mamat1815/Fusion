package com.theartemis.fusion.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.theartemis.fusion.R
import com.theartemis.fusion.data.DataRepository
import com.theartemis.fusion.data.Post
import com.theartemis.fusion.data.remote.RemoteDataSource
import com.theartemis.fusion.databinding.FragmentHomeBinding
import com.theartemis.fusion.ui.adapter.PostAdapter
import com.theartemis.fusion.ui.fragments.addpost.AddPostViewModel
import com.theartemis.fusion.ui.signin.SignInViewModel
import com.theartemis.fusion.utility.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    private lateinit var postList: ArrayList<Post>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dataRepository = DataRepository(RemoteDataSource())
        val viewModelFactory = ViewModelFactory(dataRepository)
        viewModel = ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]

        auth = FirebaseAuth.getInstance()

        postList = arrayListOf()

        binding.apply {
            val name = auth.currentUser?.displayName
            tvHomeName.text = getMiddleName(name!!)

            rvPost.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            viewModel.fetchPost().observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    postList = it as ArrayList<Post>
                    val postAdapter = PostAdapter(postList)
                    binding.rvPost.adapter = postAdapter
                }
            }


        }

    }

    private fun getMiddleName(fullName: String): String {
        val nameParts = fullName.split(" ")
        return if (nameParts.size > 2) {
            nameParts[1] // Menyimpan nama tengah jika tersedia
        } else {
            nameParts[0]
        }
    }


}