package com.expensivebelly.syncloggedinworkmanager.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.expensivebelly.syncloggedinworkmanager.R
import com.expensivebelly.syncloggedinworkmanager.applicationComponent
import com.expensivebelly.syncloggedinworkmanager.data.UploadWorker
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview_message.text = intent.getStringExtra(KEY_DISPLAY_NAME)

        button_logout.setOnClickListener {
            applicationComponent?.loginRepository()?.logout()
            WorkManager.getInstance().cancelAllWorkByTag(TAG_UPLOAD)
            startActivity(intentFor<LoginActivity>().newTask().clearTask())
        }

        button_upload.setOnClickListener {
            val oneTimeWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                .addTag(TAG_UPLOAD)
                .setConstraints(
                    Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                ).build()
            WorkManager.getInstance().enqueue(oneTimeWorkRequest)
            longToast("Upload scheduled...")
        }
    }

    companion object {
        private const val KEY_DISPLAY_NAME = "KEY_DISPLAY_NAME"
        private const val TAG_UPLOAD = "TAG_UPLOAD"

        fun start(activity: Activity, displayMessage: String) =
            activity.startActivity(
                Intent(activity, MainActivity::class.java).putExtra(
                    KEY_DISPLAY_NAME,
                    displayMessage
                )
            )
    }
}
