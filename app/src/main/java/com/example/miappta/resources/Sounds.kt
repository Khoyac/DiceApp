package com.example.miappta.resources

import android.content.Context
import android.media.MediaPlayer
import com.example.miappta.R

class Sounds {

    companion object {

        private lateinit var btnRoll: MediaPlayer
        private val soundRoll = R.raw.diceroll

        fun onRollDice(ActivityContext: Context) {
            btnRoll = MediaPlayer.create( ActivityContext, soundRoll )
            btnRoll.setVolume(100F, 100F)
            btnRoll.start()
        }
    }
}