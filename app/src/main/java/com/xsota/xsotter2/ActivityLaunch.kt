package com.xsota.xsotter2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton

/**
 * アプリ起動時に実行される
 * Twitterのログイン状態により、次に実行するActivityが変わる
 */
class ActivityLaunch : AppCompatActivity() {

    private val loginButton: TwitterLoginButton by lazy {
        findViewById(R.id.twitter_login_button) as TwitterLoginButton
    }

    private val textView: TextView by lazy {
        findViewById(R.id.text) as TextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (Twitter.getSessionManager().activeSession == null){
            textView.text = "ログインしてない"
        } else {
            textView.text = "ログインしてる"
        }

        loginButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                val session = result.data
                val msg = "@" + session.userName + " logged in! (#" + session.userId + ")"

                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            }

            override fun failure(exception: TwitterException) {
                Log.d("TwitterKit", "Login with Twitter failure", exception)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        loginButton.onActivityResult(requestCode, resultCode, data)
    }

}
