package com.theartemis.fusion.ui.fragments.addteam

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.theartemis.fusion.R
import com.theartemis.fusion.databinding.FragmentAddTeamBinding

class AddTeamFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = AddTeamFragment()
    }

    private lateinit var viewModel: AddTeamViewModel
    private lateinit var binding: FragmentAddTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTeamBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddTeamViewModel::class.java)
        binding.btnPost.setOnClickListener { dismiss() }
        binding.circleImageView.setOnClickListener { dismiss() }
    }

}