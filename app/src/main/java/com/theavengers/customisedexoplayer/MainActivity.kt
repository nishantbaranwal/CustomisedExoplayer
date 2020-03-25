package com.theavengers.customisedexoplayer

import android.content.ComponentName
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.DefaultTimeBar
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_controls.*


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val trackSelector = DefaultTrackSelector()
        val player: SimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext,trackSelector)
        simpleExoPlayerView.player = player
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            applicationContext,
            Util.getUserAgent(applicationContext, "yourApplicationName")
        )
        val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
        player.prepare(videoSource)
        exo_pause.setOnFocusChangeListener(FocusChangeListener())
        exo_play.setOnFocusChangeListener(FocusChangeListener())
        exo_progress.setOnFocusChangeListener(FocusChangeListener())
        iBHome.setOnFocusChangeListener(FocusChangeListener())
        iBInfo.setOnFocusChangeListener(FocusChangeListener())
        iBCaption.setOnFocusChangeListener(FocusChangeListener())

        iBCaption.requestFocus()
    }
    inner class FocusChangeListener:View.OnFocusChangeListener{
        override fun onFocusChange(v: View?, hasFocus: Boolean) {
            if(v is ImageButton) {
                if (hasFocus) v.setColorFilter(Color.RED) else v.setColorFilter(Color.TRANSPARENT)
            }
            else
                if(v is DefaultTimeBar)
                    if(hasFocus)exo_progress.setScrubberColor(Color.RED) else exo_progress.setScrubberColor(Color.GRAY)
        }
    }
}
