package com.theartemis.fusion.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.theartemis.fusion.R
import com.theartemis.fusion.data.TeamEntity
import com.theartemis.fusion.databinding.ItemTeamBinding
import com.theartemis.fusion.ui.detailpost.DetailPostActivity
import com.theartemis.fusion.ui.detailteam.DetailTeamActivity
import com.theartemis.fusion.utility.Utility.loadImage

class TeamAdapter(private val listTeam: ArrayList<TeamEntity>):
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    class TeamViewHolder(val binding: ItemTeamBinding):ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder =
        TeamViewHolder(ItemTeamBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val currentTeam = listTeam[position]

        holder.apply {
            binding.apply {
                currentTeam.let {
                    tvTeamTitle.text = it.title
                    tvTeamDecription.text = it.description
                    imgTeamProfile.loadImage("R.drawable.fusion_logo")
                    val memberAdapter = MemberAdapter(it.members.values.toList())
                    rvMembers.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = memberAdapter
                    }

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailTeamActivity::class.java)

                        itemView.context.startActivity(intent)
                    }

                }
            }

        }
    }
}