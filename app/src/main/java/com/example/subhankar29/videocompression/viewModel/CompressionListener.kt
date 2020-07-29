package com.example.subhankar29.videocompression.viewModel

interface CompressionListener {

    fun compressionFinished(
        status: Int,
        isVideo: Boolean,
        fileOutputPath: String?
    )

    fun onFailure(message: String?)

    fun onProgress(progress: Int)
}