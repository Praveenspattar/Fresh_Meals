package com.myapps.fresh_meals

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.myapps.fresh_meals.Api.Api_Interface
import com.myapps.fresh_meals.Utils.constants
import com.myapps.fresh_meals.databinding.ActivityFullScreenBinding
import com.myapps.fresh_meals.repository.MealsRepository
import com.myapps.fresh_meals.viewModel.MealsViewModel
import com.myapps.fresh_meals.viewModel.viewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.util.regex.Matcher
import java.util.regex.Pattern


class FullScreenActivity : AppCompatActivity() {

    lateinit var binding : ActivityFullScreenBinding
    lateinit var viewModel : MealsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api by lazy { constants.getRetrofitInstant().create(Api_Interface::class.java) }
        val repo = MealsRepository(api)
        val factory = viewModelFactory(repo)
        viewModel = ViewModelProvider(this,factory)[MealsViewModel::class.java]

        val intent = intent.getStringExtra("meal")
        val mealId = intent.toString()

        if (mealId != "") {
            viewModel.getMealDetail(mealId)
            Log.d("mealidis ", "id $mealId")
        } else {
            Log.d("meal id is empty ", "meal id  $mealId")
        }

        viewModel.liveDataMeal.observe(this){
            Log.d(" thedata"," data ${it.meals[0]}")
            binding.tvMealName.text = it.meals[0].strMeal
            binding.tvInstruction.text = it.meals[0].strInstructions

//            binding.videoView.setVideoURI(Uri.parse(it.meals[0].strYoutube))
//            binding.videoView.requestFocus()
//            binding.videoView.start()

            val pattern =
                "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"

            val compiledPattern: Pattern = Pattern.compile(pattern)
            val matcher: Matcher =
                compiledPattern.matcher(it.meals[0].strYoutube) //url is youtube url for which you want to extract the id.

            val videoId: String = if (matcher.find()) {
                matcher.group()
            } else {
                "S0Q4gqBUs7c"
            }

            this.lifecycle.addObserver(binding.videoView)
            binding.videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    //val videoId = "S0Q4gqBUs7c"
                    youTubePlayer.loadVideo(videoId, 0F)
                }
            })


//            binding.videoView.setVideoPath(it.meals[0].strYoutube)
//
//            binding.videoView.setOnPreparedListener { mp ->
//                mp.start()
//            }
            //binding.videoView.autofillType

//            binding.videoView.settings.javaScriptEnabled = true
//            binding.videoView.settings.pluginState = WebSettings.PluginState.ON
//            binding.videoView.loadUrl(it.meals[0].strYoutube)
//            binding.videoView.webChromeClient = WebChromeClient()
        }

    }
}