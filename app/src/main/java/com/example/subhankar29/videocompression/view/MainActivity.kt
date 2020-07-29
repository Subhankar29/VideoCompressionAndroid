package com.example.subhankar29.videocompression.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.subhankar29.videocompression.R
import com.example.subhankar29.videocompression.databinding.ActivityMainBinding
import com.example.subhankar29.videocompression.utils.Constant.Companion.intentConst
import com.example.subhankar29.videocompression.utils.getPath
import com.example.subhankar29.videocompression.viewModel.SelectVideoViewModel


class MainActivity : AppCompatActivity(){

    private var SELECT_VIDEO = 1
    lateinit var viewModel: SelectVideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(SelectVideoViewModel::class.java)
        dataBindingUtil.selectVideo = viewModel
        observeViewModel()
    }

    private fun observeViewModel() {
        observeChooseVideo()
    }

    private fun observeChooseVideo() {
        viewModel.showVideoPicker.observe(this, Observer { showVideoPicker ->
            showVideoPicker?.let{
                if(it){
                    val i = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(i, SELECT_VIDEO)
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if (data != null) {
                val contentURI: Uri? = data.getData()
                val selectedVideoPath = getPath(contentURI, this)
                if (contentURI != null && selectedVideoPath!=null) {
                    viewModel.onActivityResult(contentURI, selectedVideoPath)
                }
                if(selectedVideoPath!=null){
                    val intent = Intent(this, CompressActivity::class.java)
                    intent.putExtra(intentConst, viewModel.videoPath.value)
                    startActivity(intent)
                }
            }

        }
    }
}