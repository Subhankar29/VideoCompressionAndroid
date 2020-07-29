package com.example.subhankar29.videocompression.viewModel

import android.view.View
import android.widget.VideoView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.subhankar29.videocompression.utils.Constant

class PlayVideoViewModel : ViewModel() {

    val videoPath: MutableLiveData<String> = MutableLiveData()
    val playVideo: MutableLiveData<Boolean> = MutableLiveData()
    val isVideoPlaying: MutableLiveData<Boolean> = MutableLiveData()

    init {
        videoPath.value = Constant.outputPath
    }

    fun playVideoOnButtonClick(view: View, videoView: VideoView){
        playVideo.value = true
        if(videoView.isPlaying){
            isVideoPlaying.value = true
        }
    }
}