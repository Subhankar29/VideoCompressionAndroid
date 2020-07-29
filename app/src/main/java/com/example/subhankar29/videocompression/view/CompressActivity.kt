package com.example.subhankar29.videocompression.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.subhankar29.videocompression.utils.CompressVideo
import com.example.subhankar29.videocompression.R
import com.example.subhankar29.videocompression.databinding.ActivityCompressBinding
import com.example.subhankar29.videocompression.utils.Constant
import com.example.subhankar29.videocompression.utils.toast
import com.example.subhankar29.videocompression.viewModel.CompressionListener
import com.example.subhankar29.videocompression.viewModel.SelectVideoViewModel

class CompressActivity : AppCompatActivity(){

    lateinit var path: String
    lateinit var viewModel: SelectVideoViewModel
    lateinit var dataBindingUtil: ActivityCompressBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingUtil = DataBindingUtil.setContentView(this, R.layout.activity_compress)
        viewModel = ViewModelProviders.of(this).get(SelectVideoViewModel::class.java)
        dataBindingUtil.convertVideo = viewModel

        observableModels()
    }

    private fun observableModels() {
        obserConvertButtonClick()
        initView()
    }

    private fun initView() {
        path = intent.getStringExtra(Constant.intentConst)
        viewModel.videoPath.value = path
    }

    private fun obserConvertButtonClick() {
        viewModel.converVideoButton.observe(this, Observer { converVideoButton ->
            converVideoButton?.let{
                if(it){
                    startMediaCompression()
                }
            }
        })
    }

    private fun startMediaCompression() {

            dataBindingUtil.progressBar.visibility = View.VISIBLE
            dataBindingUtil.videoView.visibility = View.INVISIBLE
            dataBindingUtil.bitRate.visibility = View.INVISIBLE
            dataBindingUtil.buttonConvert.visibility = View.INVISIBLE

            val mVideoCompressor =
                CompressVideo(this)

            mVideoCompressor.startCompressing(path, dataBindingUtil.bitRate.text.toString() , object:
                CompressionListener {
                override fun compressionFinished(status: Int, isVideo: Boolean, fileOutputPath: String?) {
                    val i = Intent(this@CompressActivity, PlayVideoActivity::class.java)
                    startActivity(i)
                }
                override fun onFailure(message: String?) {
                    toast(R.string.failed_Compression.toString())
                    dataBindingUtil.progressBar.visibility = View.INVISIBLE
                    dataBindingUtil.videoView.visibility = View.VISIBLE
                    dataBindingUtil.bitRate.visibility = View.VISIBLE
                    dataBindingUtil.buttonConvert.visibility = View.VISIBLE
                }
                override fun onProgress(progress: Int) {

                }
            })
    }
}