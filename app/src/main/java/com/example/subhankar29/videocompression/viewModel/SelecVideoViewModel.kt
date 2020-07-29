package com.example.subhankar29.videocompression.viewModel

import android.net.Uri
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.subhankar29.videocompression.model.VideoInfo

class SelectVideoViewModel : ViewModel() {

    val videoUri: MutableLiveData<Uri> = MutableLiveData()
    var videoPath: MutableLiveData<String> = MutableLiveData()
    val showVideoPicker: MutableLiveData<Boolean> = MutableLiveData()
    val converVideoButton: MutableLiveData<Boolean> = MutableLiveData()
    val progressVisible: MutableLiveData<Boolean> = MutableLiveData()
    val videoInfo: MutableLiveData<VideoInfo> = MutableLiveData()

    fun selectVideo(view: View){
        showVideoPicker.value = true
    }

    fun onActivityResult(uri: Uri, path: String){
        videoUri.value = uri
        videoPath.value = path
        videoInfo.value = VideoInfo(videoPath.value!!)
    }

    fun convertVideoOnButtonClick(view: View){
        converVideoButton.value = true
        progressVisible.value = true
    }
}