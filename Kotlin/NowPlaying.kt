package com.example.groove

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.groove.databinding.FragmentNowPlayingBinding

class NowPlaying : Fragment() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var binding: FragmentNowPlayingBinding
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        requireContext().theme.applyStyle(MainActivity.currentTheme[MainActivity.themeIndex], true)
        val view = inflater.inflate(R.layout.fragment_now_playing, container, false)
        binding = FragmentNowPlayingBinding.bind(view)
        binding.root.visibility = View.INVISIBLE
        binding.playPauseBtnNP.setOnClickListener {
            if (PlayerActivity.isPlaying) pauseMusic() else playMusic()
        }
        /**binding.nextBtnNP.setOnClickListener {
            setSongPosition(increment = true)
            PlayerActivity.musicService!!.createMediaPlayer()
            PlayerActivity.musicService!!.showNotification(R.drawable.pause_btn)
            playMusic()
        }
        binding.previousBtnNP.setOnClickListener {
            setSongPosition(increment = false)
            PlayerActivity.musicService!!.createMediaPlayer()
            PlayerActivity.musicService!!.showNotification(R.drawable.pause_btn)
            playMusic()
        } **/
        binding.root.setOnClickListener {
            val intent = Intent(requireContext(), PlayerActivity::class.java)
            intent.putExtra("index", PlayerActivity.songPosition)
            intent.putExtra("class", "NowPlaying")
            ContextCompat.startActivity(requireContext(), intent, null)
            binding.visualizer02.visibility = View.VISIBLE
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        if (PlayerActivity.musicService != null)
        {
            binding.root.visibility = View.VISIBLE
            binding.songNameNP.isSelected = true
            Glide.with(requireContext())
                .load(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
                .apply(RequestOptions().placeholder(R.drawable.eun_hye).centerCrop())
                .into(binding.songImgNP)
            binding.songNameNP.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
            binding.songArtist.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].artist
            if (PlayerActivity.isPlaying) binding.playPauseBtnNP.setIconResource(R.drawable.pause_btn)
            else binding.playPauseBtnNP.setIconResource(R.drawable.play_btn)
        }
    }

    private fun playMusic() {
        PlayerActivity.musicService!!.mediaPlayer!!.start()
        binding.playPauseBtnNP.setIconResource(R.drawable.pause_btn)
        PlayerActivity.musicService!!.showNotification(R.drawable.pause_btn)
        PlayerActivity.binding.playPauseBtn.setIconResource(R.drawable.pause_btn)
        PlayerActivity.isPlaying = true
        binding.visualizer02.visibility = View.VISIBLE
    }
    private fun pauseMusic() {
        PlayerActivity.musicService!!.mediaPlayer!!.pause()
        binding.playPauseBtnNP.setIconResource(R.drawable.play_btn)
        PlayerActivity.musicService!!.showNotification(R.drawable.play_btn)
        PlayerActivity.binding.playPauseBtn.setIconResource(R.drawable.play_btn)
        PlayerActivity.isPlaying = false
        binding.visualizer02.visibility = View.INVISIBLE
    }
}