package com.example.groove

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.system.exitProcess

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            ApplicationClass.PREVIOUS -> prevNextSong(increment = false, context = context!!)
            ApplicationClass.PLAY -> if (PlayerActivity.isPlaying) pauseMusic() else playMusic()
            ApplicationClass.NEXT -> prevNextSong(increment = true, context = context!!)
            ApplicationClass.EXIT -> {
                exitApplication()
            }
        }
    }

    private fun playMusic() {
        PlayerActivity.isPlaying = true
        PlayerActivity.musicService!!.mediaPlayer!!.start()
        PlayerActivity.musicService!!.showNotification(R.drawable.pause_btn)
        PlayerActivity.binding.playPauseBtn.setIconResource(R.drawable.pause_btn)
        NowPlaying.binding.playPauseBtnNP.setIconResource(R.drawable.pause_btn)
        PlayerActivity.binding.visualizer.visibility = View.VISIBLE
        NowPlaying.binding.visualizer02.visibility = View.VISIBLE
    }

    private fun pauseMusic() {
        PlayerActivity.isPlaying = false
        PlayerActivity.musicService!!.mediaPlayer!!.pause()
        PlayerActivity.musicService!!.showNotification(R.drawable.play_btn)
        PlayerActivity.binding.playPauseBtn.setIconResource(R.drawable.play_btn)
        NowPlaying.binding.playPauseBtnNP.setIconResource(R.drawable.play_btn)
        PlayerActivity.binding.visualizer.visibility = View.INVISIBLE
        NowPlaying.binding.visualizer02.visibility = View.INVISIBLE
    }

    private fun prevNextSong(increment: Boolean, context: Context) {
        setSongPosition(increment = increment)
        PlayerActivity.musicService!!.createMediaPlayer()
        Glide.with(context)
            .load(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.eun_hye)).centerCrop()
            .into(PlayerActivity.binding.songImgPA)
        PlayerActivity.binding.songNamePA.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
        PlayerActivity.binding.songArtistPA.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].artist
        NowPlaying.binding.songNameNP.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
        NowPlaying.binding.songArtist.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].artist
        Glide.with(context)
            .load(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.eun_hye).centerCrop())
            .into(NowPlaying.binding.songImgNP)
        playMusic()
        PlayerActivity.fIndex = favouriteChecker(PlayerActivity.musicListPA[PlayerActivity.songPosition].id)
        if (PlayerActivity.isFavourite) PlayerActivity.binding.favouriteBtnPA.setImageResource(R.drawable.favourites_03)
        else PlayerActivity.binding.favouriteBtnPA.setImageResource(R.drawable.favourites)
    }
}