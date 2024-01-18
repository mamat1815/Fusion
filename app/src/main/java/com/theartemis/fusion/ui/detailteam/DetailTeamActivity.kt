package com.theartemis.fusion.ui.detailteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.theartemis.fusion.R
import com.theartemis.fusion.databinding.ActivityDetailTeamBinding
import com.theartemis.fusion.ui.mentor.MentorActivity

class DetailTeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTeamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnMentor.setOnClickListener {
                val intent = Intent(this@DetailTeamActivity, MentorActivity::class.java)
                startActivity(intent)
            }
        }


    }
}