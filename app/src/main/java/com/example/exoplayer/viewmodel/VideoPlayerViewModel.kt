package com.example.exoplayer.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.exoplayer.model.VideoData
import com.example.exoplayer.model.VideoList

class VideoPlayerViewModel : ViewModel() {
    //var exo
    private var exoPlayer: ExoPlayer? = null

    //
    var index: Int = 0

    //list video ha load ro kardam
    var videoList: List<VideoData> = listOf()

    fun initializePlayer(context: Context) {
        exoPlayer = ExoPlayer.Builder(context).build()

    }

    fun releasePlayer() {
        exoPlayer?.playWhenReady = false
        exoPlayer?.stop()
        exoPlayer?.release()
    }

    fun playVideo() {
        exoPlayer?.apply {
            stop()
            clearMediaItems()
            setMediaItem(MediaItem.fromUri(Uri.parse(VideoList[index].videoUrl)))
            playWhenReady = true
            prepare()
            play()

        }
    }

    fun playerViewBuilder(context: Context):PlayerView{
        val player = PlayerView(context).apply {
            player = exoPlayer
        }
        return  player

    }
}