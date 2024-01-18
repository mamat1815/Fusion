package com.theartemis.fusion.ui.fragments.team

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.theartemis.fusion.R
import com.theartemis.fusion.data.Post
import com.theartemis.fusion.data.TeamEntity
import com.theartemis.fusion.databinding.FragmentTeamBinding
import com.theartemis.fusion.ui.adapter.TeamAdapter
import com.theartemis.fusion.ui.fragments.addpost.AddPostFragment
import com.theartemis.fusion.ui.fragments.addteam.AddTeamFragment
import com.theartemis.fusion.utility.DataDummy
import com.theartemis.fusion.utility.Utility

class TeamFragment : Fragment() {

    companion object {
        fun newInstance() = TeamFragment()
    }

    private lateinit var viewModel: TeamViewModel
    private lateinit var binding: FragmentTeamBinding
    private lateinit var teamList: ArrayList<TeamEntity>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        teamList = arrayListOf()
        val teams: List<TeamEntity> = DataDummy.generateDummyTeams()
        teamList = teams as ArrayList<TeamEntity>
        val teamsAdapter = TeamAdapter(teamList)

        binding.apply {
            btnTeamBuildTeam.setOnClickListener {
                showBottomSheet()
            }

            rvTeam.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = teamsAdapter
            }
        }




    }

    private fun showBottomSheet() {
        val bottomSheetFragment = AddTeamFragment()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

}