package com.example.subhankar29.videocompression.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.subhankar29.videocompression.R
import com.example.subhankar29.videocompression.databinding.ActivityPlayVideoBinding
import com.example.subhankar29.videocompression.viewModel.PlayVideoViewModel
import kotlinx.android.synthetic.main.activity_compress.*
import kotlinx.android.synthetic.main.activity_compress.videoView
import kotlinx.android.synthetic.main.activity_play_video.*

class PlayVideoActivity : AppCompatActivity() {

    lateinit var viewModel: PlayVideoViewModel
    lateinit var dataBindingUtil: ActivityPlayVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingUtil = DataBindingUtil.setContentView(this, R.layout.activity_play_video)
        viewModel = ViewModelProviders.of(this).get(PlayVideoViewModel::class.java)
        dataBindingUtil.playVideo = viewModel

        observableModel()
        videoView.setOnCompletionListener { playPauseButton.setImageResource(R.drawable.ic_play) }
    }

    private fun observableModel() {
        obserPlayVideo()
    }

    private fun obserPlayVideo() {
        viewModel.playVideo.observe(this, Observer { playVideo ->
            playVideo?.let{
                if(it){
                    videoView.start()
                    viewModel.playVideo.value = false
                    playPauseButton.setImageResource(R.drawable.ic_pause)
                }
            }
        })
    }


}