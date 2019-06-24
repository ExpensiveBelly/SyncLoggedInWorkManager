package com.expensivebelly.syncloggedinworkmanager.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.expensivebelly.syncloggedinworkmanager.R
import com.expensivebelly.syncloggedinworkmanager.applicationComponent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class MainActivity : AppCompatActivity() {

    companion object {

        private const val KEY_DISPLAY_NAME = "KEY_DISPLAY_NAME"

        fun start(activity: Activity, displayMessage: String) =
            activity.startActivity(
                Intent(activity, MainActivity::class.java).putExtra(
                    KEY_DISPLAY_NAME,
                    displayMessage
                )
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview_message.text = intent.getStringExtra(KEY_DISPLAY_NAME)

        button_logout.setOnClickListener {
            applicationComponent?.loginRepository()?.logout()
            startActivity(intentFor<MainActivity>().singleTop().clearTop())
        }
    }
}
