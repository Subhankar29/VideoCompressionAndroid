package com.example.subhankar29.videocompression.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.widget.Toast
import java.io.File

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun getPath(uri: Uri?, context: Context): String? {
    val projection = arrayOf(MediaStore.Video.Media.DATA)
    val cursor = context.contentResolver.query(uri!!, projection, null, null, null)
    if (cursor != null) {
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    } else
        return null
}

fun getAppDir(): String? {
    var outputPath =
        Environment.getExternalStorageDirectory().absolutePath
    outputPath += "/" + "VideoCompression"
    var file = File(outputPath)
    if (!file.exists()) {
        file.mkdir()
    }
    outputPath += "/" + "compressedVideo"
    file = File(outputPath)
    if (!file.exists()) {
        file.mkdir()
    }
    return outputPath
}
