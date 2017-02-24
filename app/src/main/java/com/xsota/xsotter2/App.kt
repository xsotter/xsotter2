package com.xsota.xsotter2

import android.app.Application
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import io.fabric.sdk.android.Fabric

/**
 * Created by sota on 2017/02/25.
 */

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        // TwitterKit初期化処理
        val authConfig = TwitterAuthConfig(getString(R.string.consumer_key), getString(R.string.consumer_secret))
        Fabric.with(this, Twitter(authConfig))
    }
}
