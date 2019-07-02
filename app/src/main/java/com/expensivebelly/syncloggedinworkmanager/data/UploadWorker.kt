package com.expensivebelly.syncloggedinworkmanager.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.expensivebelly.syncloggedinworkmanager.applicationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class UploadWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override val coroutineContext = Dispatchers.IO
    
    private val tag = "UploadWorker"

    override suspend fun doWork(): Result {
        val loggedIn = appContext.applicationComponent?.loginRepository()?.isLoggedIn
        Log.d(tag, "Is user logged in? $loggedIn ")
        if (loggedIn == true) {
            Log.d(tag, "Uploading...")
            delay(5000)
            Log.d(tag, "Upload complete!")
        } else {
            Log.d(tag, "User is not logged in. Upload can't take place")
        }

        // Indicate whether the task finished successfully with the Result
        return Result.success()
    }
}