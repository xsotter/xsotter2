package com.xsota.xsotter2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class MainActivity : AppCompatActivity() {

    private val loginButton: TwitterLoginButton by lazy {
        findViewById(R.id.twitter_login_button) as TwitterLoginButton
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

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
