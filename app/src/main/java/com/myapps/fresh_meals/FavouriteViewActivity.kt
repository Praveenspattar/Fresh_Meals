package com.myapps.fresh_meals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapps.fresh_meals.databinding.ActivityFavouriteViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class FavouriteViewActivity : AppCompatActivity() {

    lateinit var binding : ActivityFavouriteViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealName = intent.getStringExtra("FavMealName")
        val instruction = intent.getStringExtra("FavMealIn")
        val youtubeId = intent.getStringExtra("FavMealYT")

        binding.tvInstruction.text = instruction.toString()
        binding.tvMealName.text = mealName.toString()

        val videoId = if (youtubeId != null){
            youtubeId
        }else{
            "S0Q4gqBUs7c"
        }

        binding.videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0F)
            }
        })

    }
}