package ru.ridkeim.keyboardexample

import android.app.Application
import timber.log.Timber

class KeyBoardExampleApplication() : Application(){
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}