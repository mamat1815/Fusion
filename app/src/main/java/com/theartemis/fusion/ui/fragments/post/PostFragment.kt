package com.theartemis.fusion.ui.fragments.post

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theartemis.fusion.databinding.FragmentPostBinding
import com.theartemis.fusion.ui.fragments.addpost.AddPostFragment

class PostFragment : Fragment() {

    companion object {
        fun newInstance() = PostFragment()
    }

    private lateinit var viewModel: PostViewModel
    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater, container, false)

        showBottomSheet()

        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[PostViewModel::class.java]

    }

    private fun showBottomSheet() {
        val bottomSheetFragment = AddPostFragment()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

}