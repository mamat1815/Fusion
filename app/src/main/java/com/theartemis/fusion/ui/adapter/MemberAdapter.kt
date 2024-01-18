package com.theartemis.fusion.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theartemis.fusion.data.TeamEntity
import com.theartemis.fusion.databinding.ItemTeamsMemberBinding

class MemberAdapter(private val members: List<TeamEntity.MemberEntity>) :
    RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    class MemberViewHolder(val binding: ItemTeamsMemberBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder
    = MemberViewHolder(ItemTeamsMemberBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]
        holder.apply {
            binding.apply {
                tvMember.text = member.displayName
            }
        }
    }

    override fun getItemCount(): Int {
        return members.size
    }
}