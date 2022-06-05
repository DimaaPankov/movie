package com.sekvenie.splashpresenter

import androidx.constraintlayout.motion.widget.MotionLayout
import com.sekvenie.App
import com.sekvenie.R

class SplashPresenter {
                         private var view:SplashViewApi? = null
    fun init(_view:SplashViewApi){
        view = _view
    }
    fun destroy(){
        view = null
    }

    fun motion(){
        view?.startMotion()
}}