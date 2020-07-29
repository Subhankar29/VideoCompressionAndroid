package com.example.subhankar29.videocompression.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import com.example.subhankar29.videocompression.viewModel.CompressionListener
import com.github.hiteshsondhi88.libffmpeg.FFmpeg
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException
import java.io.File


class CompressVideo(context: Context) {

    var mContext: Context = context
    lateinit var fFmpeg: FFmpeg
    private var isFinished = false
    private var status = 3
    private val TAG = "CompressVideo"


    fun startCompressing(inputPath: String?, bitrate: String,listener: CompressionListener?) {
        if (inputPath == null || inputPath.isEmpty()) {
            Log.d(TAG, "Empty Path")
            return
        }

        val outputPath = getAppDir() + "/video_compress.mp4"
        val commandParams = arrayOfNulls<String>(26)
        commandParams[0] = "-y"
        commandParams[1] = "-i"
        commandParams[2] = inputPath
        commandParams[3] = "-s"
        commandParams[4] = "240x320"
        commandParams[5] = "-r"
        commandParams[6] = "20"
        commandParams[7] = "-c:v"
        commandParams[8] = "libx264"
        commandParams[9] = "-preset"
        commandParams[10] = "ultrafast"
        commandParams[11] = "-c:a"
        commandParams[12] = "copy"
        commandParams[13] = "-me_method"
        commandParams[14] = "zero"
        commandParams[15] = "-tune"
        commandParams[16] = "fastdecode"
        commandParams[17] = "-tune"
        commandParams[18] = "zerolatency"
        commandParams[19] = "-strict"
        commandParams[20] = "-2"
        commandParams[21] = "-b:v"
        commandParams[22] = bitrate
        commandParams[23] = "-pix_fmt"
        commandParams[24] = "yuv420p"
        commandParams[25] = outputPath

        compressVideo(commandParams, outputPath, listener)
    }

    fun compressVideo(commandParams: Array<String?>, outputPath: String, listener: CompressionListener?) {
        try {
            fFmpeg = FFmpeg.getInstance(mContext)
            fFmpeg.loadBinary(object : LoadBinaryResponseHandler(){
                override fun onStart() {}

                override fun onFailure() {}

                override fun onSuccess() {}

                override fun onFinish() {}
            })
            fFmpeg.execute(commandParams, object : FFmpegExecuteResponseHandler {
                override fun onSuccess(message: String) {
                    Log.d(TAG, message)
                }

                override fun onProgress(message: String) {
                    Log.d(TAG, message)
                }

                override fun onFailure(message: String) {
                    Log.e(TAG, message)
                }

                override fun onStart() {}
                override fun onFinish() {
                    Log.d(TAG, "FINISHED")
                    isFinished = true
                    listener?.compressionFinished(status, true, outputPath)
                }
            })

        } catch (e: FFmpegCommandAlreadyRunningException) {
            Log.e(TAG, e.toString())
        }

    }
}
