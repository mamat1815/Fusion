package com.theartemis.fusion.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.theartemis.fusion.data.Post
import com.theartemis.fusion.databinding.ItemPostBinding
import com.theartemis.fusion.ui.detailpost.DetailPostActivity

import com.theartemis.fusion.utility.Utility.loadImage

class PostAdapter(private val listPost : ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder (val binding: ItemPostBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = listPost[position]

        holder.apply {
            binding.apply {
                tvItemTitle.text = currentPost.title
                tvItemDetail.text = currentPost.description
                tvItemUsername.text = currentPost.username
                imgItemPost.loadImage(currentPost.img)
                imgItemProfile.loadImage(currentPost.userImg)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailPostActivity::class.java)
                    intent.apply {
                        putExtra(DetailPostActivity.EXTRA_ID, currentPost.id)
                    }
                    itemView.context.startActivity(intent)
                }

            }
        }

    }

    override fun getItemCount(): Int = listPost.size


}