package com.example.subhankar29.videocompression.utils

import android.net.Uri
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.subhankar29.videocompression.utils.extensions.getParentActivity

@BindingAdapter("loadVideo")
fun bindingVideo(videoView: VideoView, videoPath: MutableLiveData<String>){
    val parentActivity: AppCompatActivity? = videoView.getParentActivity()

    parentActivity?.let{
        videoPath?.let {
            videoPath.observe(parentActivity, Observer { value ->
                value?.let{
                    if(!value.isNullOrBlank()){
                        videoView.setVideoURI(Uri.parse(videoPath.value))
                        videoView.requestFocus()
                        videoView.start()
                    }
                }
            })
        }
    }
}