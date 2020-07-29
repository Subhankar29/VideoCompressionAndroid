package com.example.subhankar29.videocompression.utils


import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.example.subhankar29.videocompression.R
import com.example.subhankar29.videocompression.utils.extensions.getParentActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("playPause")
fun bindingPlayPause(view: FloatingActionButton, videoView: VideoView){
    val parentActivity: AppCompatActivity? = videoView.getParentActivity()

    parentActivity?.let{
        view.setImageResource(R.drawable.ic_pause)
    }
}