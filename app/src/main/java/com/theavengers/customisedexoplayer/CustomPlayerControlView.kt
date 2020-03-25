package com.theavengers.customisedexoplayer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.exoplayer2.ui.PlayerControlView

class CustomPlayerControlView(context:Context,attrs: AttributeSet?=null,defStyleAttr:Int)
    : PlayerControlView(context,attrs){
    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_controls,this)
    }
}